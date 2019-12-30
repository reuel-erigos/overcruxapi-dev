package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetTalentosPFCmd;
import br.com.crux.entity.AcoesCompetencia;
import br.com.crux.entity.TalentosPf;
import br.com.crux.to.AcoesCompetenciaTO;

@Component
public class AcoesCompetenciaTOBuilder {

	@Autowired private TalentosPFTOBuilder talentosPFBuilder;
	@Autowired private GetTalentosPFCmd getTalentosPFCmd;

	public AcoesCompetencia build(AcoesCompetenciaTO p) {
		AcoesCompetencia retorno = new AcoesCompetencia();

		retorno.setId(p.getId());
		retorno.setDescricao(p.getDescricao());
		retorno.setResultadoAcao(p.getResultadoAcao());
		retorno.setResultadoPrevAcao(p.getResultadoPrevAcao());
		retorno.setDataInicio(p.getDataInicio());
		retorno.setDataFim(p.getDataFim());

		Optional.ofNullable(p.getTalentosPf()).ifPresent(t -> {
			TalentosPf talento = getTalentosPFCmd.getById(t.getId());
			retorno.setTalentosPf(talento);
		});

		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public AcoesCompetenciaTO buildTO(AcoesCompetencia p) {
		AcoesCompetenciaTO retorno = new AcoesCompetenciaTO();

		if (Objects.isNull(p)) {
			return retorno;
		}

		retorno.setId(p.getId());
		retorno.setDescricao(p.getDescricao());
		retorno.setResultadoAcao(p.getResultadoAcao());
		retorno.setResultadoPrevAcao(p.getResultadoPrevAcao());
		retorno.setDataInicio(p.getDataInicio());
		retorno.setDataFim(p.getDataFim());
		retorno.setTalentosPf(talentosPFBuilder.buildTO(p.getTalentosPf()));

		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public List<AcoesCompetenciaTO> buildAll(List<AcoesCompetencia> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
