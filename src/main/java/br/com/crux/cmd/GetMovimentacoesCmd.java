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

	public List<MovimentacoesTO> getAllFilter(Long idEmpresa, Long idPrograma, Long idProjeto, String valor, final Long dataInicio, final Long dataFim, final Long dataVencimento) {
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();

		LocalDate pDataInicio     = Objects.nonNull(dataInicio) ? Java8DateUtil.getLocalDateTime(new Date(dataInicio)).toLocalDate() : null;
		LocalDate pDataFim        = Objects.nonNull(dataFim) ? Java8DateUtil.getLocalDateTime(new Date(dataFim)).toLocalDate() : null;
		LocalDate pDataVencimento = Objects.nonNull(dataVencimento) ? Java8DateUtil.getLocalDateTime(new Date(dataVencimento)).toLocalDate() : null;
		
		Optional<List<Movimentacoes>> entitys = Optional.empty();

		idEmpresa      = Objects.isNull(idEmpresa) ? null : idEmpresa;
		idPrograma     = Objects.isNull(idPrograma) ? null : idPrograma;
		idProjeto      = Objects.isNull(idProjeto) ? null : idProjeto;
		Double valorIn = StringUtils.isEmpty(valor.trim()) ? null : Double.valueOf(valor);

		entitys = repository.findByFilterOrigem(idInstituicao, idEmpresa, idPrograma, idProjeto, valorIn);

		if (entitys.isPresent()) {
			List<MovimentacoesTO> saldos = toBuilder.buildAll(entitys.get());

			if (Objects.nonNull(dataInicio) || Objects.nonNull(dataFim)) {
				saldos = saldos.stream().filter(saldo -> {
					return Java8DateUtil.isVigente(saldo.getDataDocumento().toLocalDate(), pDataInicio, pDataFim);
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

	public List<MovimentacoesTO> getAllDestino() {
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		Optional<List<Movimentacoes>> entitys = repository.getAllTipoMovimentoDestino(idInstituicao);
		if (entitys.isPresent()) {
			return toBuilder.buildAll(entitys.get());
		}
		return new ArrayList<MovimentacoesTO>();
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
