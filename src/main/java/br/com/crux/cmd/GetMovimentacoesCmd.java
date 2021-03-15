package br.com.crux.cmd;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.MovimentacoesTOBuilder;
import br.com.crux.dao.TransferenciaValoresDao;
import br.com.crux.dao.dto.TransferenciaValoresDTO;
import br.com.crux.dao.repository.MovimentacoesRepository;
import br.com.crux.entity.Movimentacoes;
import br.com.crux.exception.NotFoundException;
import br.com.crux.infra.util.Java8DateUtil;
import br.com.crux.to.MovimentacoesTO;

@Component
public class GetMovimentacoesCmd {

	@Autowired private MovimentacoesRepository repository;
	@Autowired private MovimentacoesTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	@Autowired private TransferenciaValoresDao transferenciaValoresDao;

	public List<MovimentacoesTO> getAllFilter(Long idEmpresa, Long idPrograma, Long idProjeto, String valor, 
			                                  Long dataInicioDoc, Long dataFimDoc, Long dataVencimento,
			                                  Long dataInicioMov, Long dataFimMov, 
			                                  Long dataInicioPag, Long dataFimPag,
			                                  String numeroDocumento) {
		
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();

		LocalDate pDataInicioDoc  = Objects.nonNull(dataInicioDoc) ? Java8DateUtil.getLocalDateTime(new Date(dataInicioDoc)).toLocalDate() : null;
		LocalDate pDataFimDoc     = Objects.nonNull(dataFimDoc) ? Java8DateUtil.getLocalDateTime(new Date(dataFimDoc)).toLocalDate() : null;
		LocalDate pDataVencimento = Objects.nonNull(dataVencimento) ? Java8DateUtil.getLocalDateTime(new Date(dataVencimento)).toLocalDate() : null;
		LocalDate pDataInicioMov  = Objects.nonNull(dataInicioMov) ? Java8DateUtil.getLocalDateTime(new Date(dataInicioMov)).toLocalDate() : null;
		LocalDate pDataFimMov     = Objects.nonNull(dataFimMov) ? Java8DateUtil.getLocalDateTime(new Date(dataFimMov)).toLocalDate() : null;
		LocalDate pDataInicioPag  = Objects.nonNull(dataInicioPag) ? Java8DateUtil.getLocalDateTime(new Date(dataInicioPag)).toLocalDate() : null;
		LocalDate pDataFimPag     = Objects.nonNull(dataFimPag) ? Java8DateUtil.getLocalDateTime(new Date(dataFimPag)).toLocalDate() : null;

		Optional<List<Movimentacoes>> entitys = Optional.empty();

		idEmpresa       = Objects.isNull(idEmpresa) ? null : idEmpresa;
		idPrograma      = Objects.isNull(idPrograma) ? null : idPrograma;
		idProjeto       = Objects.isNull(idProjeto) ? null : idProjeto;
		numeroDocumento = StringUtils.isEmpty(numeroDocumento) ? null : numeroDocumento;
		Double valorIn  = StringUtils.isEmpty(valor) ? null : Double.valueOf(valor);

		entitys = repository.findByFilterOrigem(idInstituicao, idEmpresa, idPrograma, idProjeto, valorIn, numeroDocumento);

		if (entitys.isPresent()) {
			List<MovimentacoesTO> saldos = toBuilder.buildAll(entitys.get());

			if (Objects.nonNull(pDataInicioDoc) || Objects.nonNull(pDataFimDoc)) {
				saldos = saldos.stream().filter(saldo -> {
					return Objects.nonNull(saldo.getDataDocumento()) && Java8DateUtil.isVigente(saldo.getDataDocumento(), pDataInicioDoc, pDataFimDoc);
				}).collect(Collectors.toList());
			}
			
			if (Objects.nonNull(pDataInicioMov) || Objects.nonNull(pDataFimMov)) {
				saldos = saldos.stream().filter(saldo -> {
					return Objects.nonNull(saldo.getDataMovimentacao()) && Java8DateUtil.isVigente(saldo.getDataMovimentacao().toLocalDate(), pDataInicioMov, pDataFimMov);
				}).collect(Collectors.toList());
			}
			
			if (Objects.nonNull(pDataInicioPag) || Objects.nonNull(pDataFimPag)) {
				saldos = saldos.stream().filter(saldo -> {
					return saldo.getPagamentosFatura().stream().anyMatch( fatura -> Java8DateUtil.isVigente(fatura.getDataPagamento().toLocalDate(), pDataInicioPag, pDataFimPag));
				}).collect(Collectors.toList());
			}			

			if (Objects.nonNull(pDataVencimento) ) {
				saldos = saldos.stream().filter(saldo -> {
					return saldo.getFaturas().stream().anyMatch( fatura -> fatura.getDataVencimento().toLocalDate().equals(pDataVencimento));
				}).collect(Collectors.toList());
			}
			
			return saldos;
		}

		return new ArrayList<MovimentacoesTO>();
	}

	public List<TransferenciaValoresDTO> getAllTransferenciaValores(Long idContaOrigem, Long idContaDestino, Double valor, Long data) {
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		
		idContaOrigem    = Objects.isNull(idContaOrigem) ? null : idContaOrigem;
		idContaDestino   = Objects.isNull(idContaDestino) ? null : idContaDestino;
		valor            = Objects.isNull(valor) ? null : valor;
		LocalDate pData  = Objects.nonNull(data) ? Java8DateUtil.getLocalDateTime(new Date(data)).toLocalDate() : null;
		
		Optional<List<TransferenciaValoresDTO>> entitys = transferenciaValoresDao.getAllFilter(idInstituicao, idContaOrigem, idContaDestino, pData, valor);
		if (!entitys.isPresent()) {
			return new ArrayList<TransferenciaValoresDTO>();
		}
		
		return entitys.get();
	}

	public List<MovimentacoesTO> getAllOrigem() {
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		Optional<List<Movimentacoes>> entitys = repository.getAllTipoMovimentoOrigem(idInstituicao);
		if (entitys.isPresent()) {
			return toBuilder.buildAll(entitys.get());
		}
		return new ArrayList<MovimentacoesTO>();
	}

	public MovimentacoesTO getTOById(Long id) {
		Movimentacoes entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Entidade não encontrada"));
		return toBuilder.buildTO(entity);
	}

	public Movimentacoes getById(Long id) {
		return repository.findById(id).orElseGet(null);
	}

	
}
