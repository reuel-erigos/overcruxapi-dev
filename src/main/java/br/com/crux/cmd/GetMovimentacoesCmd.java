package br.com.crux.cmd;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

	public List<MovimentacoesTO> getAllFilter(Long idEmpresa, Long idPrograma, Long idProjeto, String valor, final LocalDateTime dataInicio, final LocalDateTime dataFim) {
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();

		Optional<List<Movimentacoes>> entitys = Optional.empty();

		idEmpresa  = Objects.isNull(idPrograma) ? null : idEmpresa;
		idPrograma = Objects.isNull(idPrograma) ? null : idPrograma;
		idProjeto  = Objects.isNull(idProjeto) ? null : idProjeto;
		valor      = StringUtils.isEmpty(valor.trim()) ? null : valor;

		entitys = repository.findByFilterOrigem(idInstituicao, idEmpresa, idPrograma, idProjeto, valor);

		if (entitys.isPresent()) {
			List<MovimentacoesTO> saldos = toBuilder.buildAll(entitys.get());

			if (Objects.nonNull(dataInicio) || Objects.nonNull(dataFim)) {
				return saldos.stream().filter(saldo -> {
					return Java8DateUtil.between(saldo.getDataDocumento().toLocalDate(), dataInicio.toLocalDate(), dataFim.toLocalDate());
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
