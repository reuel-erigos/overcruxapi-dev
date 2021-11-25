package br.com.crux.cmd;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.MovimentacaoContabilTOBuilder;
import br.com.crux.dao.AtualizarSaldoContaContabilDao;
import br.com.crux.dao.MovimentacaoContabilDao;
import br.com.crux.dao.dto.SaldoContaContabilDTO;
import br.com.crux.infra.util.Java8DateUtil;
import br.com.crux.to.relatorios.financeiro.MovimentacaoContabilDTO;
import br.com.crux.to.relatorios.financeiro.MovimentacaoContabilTO;
import br.com.crux.to.relatorios.financeiro.SaldoContaContabilTO;

@Component
public class GetMovimentacaoContabilCmd {

	@Autowired private MovimentacaoContabilDao       dao;
	@Autowired private MovimentacaoContabilTOBuilder toBuilder;
	@Autowired private AtualizarSaldoContaContabilDao atualizarSaldoContaContabilDao;
	
	
	public void atualizarSaldoContaContabil(Long idPlanoConta, LocalDate dataInicio) {
		atualizarSaldoContaContabilDao.atualizarSaldo(idPlanoConta, dataInicio);
	}

	public void atualizarSaldoContaContabilPrograma(Long idPlanoConta, LocalDate dataInicio, Long idPrograma) {
		atualizarSaldoContaContabilDao.atualizarSaldoPrograma(idPlanoConta, dataInicio, idPrograma);
	}
	
	public void atualizarSaldoContaContabilProjeto(Long idPlanoConta, LocalDate dataInicio, Long idProjeto) {
		atualizarSaldoContaContabilDao.atualizarSaldoProjeto(idPlanoConta, dataInicio, idProjeto);
	}
	
	public List<Integer> getContasContabeisSubordinadas(Long idPlanoConta) {
		Optional<List<Integer>> contas = Optional.ofNullable(dao.getContasContabeisSubordinadas(idPlanoConta));
		return contas.orElse(null);
	}
	
	public SaldoContaContabilTO getSaldoContaContabil(Long idPlanoConta, LocalDate dataInicio, LocalDate dataFim ) {
		Optional<SaldoContaContabilDTO> saldo = Optional.ofNullable(dao.getSaldoContaBancaria(idPlanoConta, dataInicio,  dataFim ));
		
		if(saldo.isPresent()) {
			return new SaldoContaContabilTO(saldo.get().getSaldoDataInicioContaContabil(), saldo.get().getSaldoDataFimContaContabil()) ;
		}
		
		return null;
	}
	
	public SaldoContaContabilTO getSaldoContaContabilPrograma(Long idPlanoConta, LocalDate dataInicio, LocalDate dataFim, Long idPrograma) {
		Optional<SaldoContaContabilDTO> saldo = Optional.ofNullable(dao.getSaldoContaBancariaPrograma(idPlanoConta, dataInicio,  dataFim, idPrograma ));
		
		if(saldo.isPresent()) {
			return new SaldoContaContabilTO(saldo.get().getSaldoDataInicioContaContabil(), saldo.get().getSaldoDataFimContaContabil()) ;
		}
		
		return null;
	}

	public SaldoContaContabilTO getSaldoContaContabilProjeto(Long idPlanoConta, LocalDate dataInicio, LocalDate dataFim, Long idProjeto) {
		Optional<SaldoContaContabilDTO> saldo = Optional.ofNullable(dao.getSaldoContaBancariaProjeto(idPlanoConta, dataInicio,  dataFim, idProjeto ));
		
		if(saldo.isPresent()) {
			return new SaldoContaContabilTO(saldo.get().getSaldoDataInicioContaContabil(), saldo.get().getSaldoDataFimContaContabil()) ;
		}
		
		return null;
	}

	
	public List<MovimentacaoContabilTO> getAllFilter(Long idcategoria, Long idPrograma, Long idProjeto, Long dataInicio, Long dataFim) {
		Optional<List<MovimentacaoContabilDTO>> entitys = Optional.empty();

		LocalDate pDataInicio = Objects.nonNull(dataInicio) ? Java8DateUtil.getLocalDateTime(new Date(dataInicio)).toLocalDate() : null;
		LocalDate pDataFim    = Objects.nonNull(dataFim) ? Java8DateUtil.getLocalDateTime(new Date(dataFim)).toLocalDate() : null;

		idcategoria     = Objects.isNull(idcategoria) ? null : idcategoria;
		idPrograma      = Objects.isNull(idPrograma) ? null : idPrograma;
		idProjeto       = Objects.isNull(idProjeto) ? null : idProjeto;
		
		entitys = dao.getAllFilter(idcategoria, idPrograma, idProjeto, pDataInicio, pDataFim);

		if (entitys.isPresent()) {
			return toBuilder.buildAllDTO(entitys.get());
		}

		return new ArrayList<MovimentacaoContabilTO>();
		
	}
	
	

	
}
