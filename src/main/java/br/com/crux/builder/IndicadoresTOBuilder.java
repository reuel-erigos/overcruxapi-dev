package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.cmd.GetObjetivoCmd;
import br.com.crux.entity.Indicadores;
import br.com.crux.entity.Objetivo;
import br.com.crux.to.IndicadoresTO;

@Component
public class IndicadoresTOBuilder {

	@Autowired private GetObjetivoCmd getObjetivoCmd;
	@Autowired private ObjetivoTOBuilder objetivoTOBuilder;

	public Indicadores build(IndicadoresTO to) {
		Indicadores entity = new Indicadores();

		entity.setIdIndicador(to.getIdIndicador());
		entity.setDataInicio(to.getDataInicio());
		entity.setDataFim(to.getDataFim());
		entity.setNome(to.getNome());

		Optional.ofNullable(to.getObjetivo()).ifPresent(obj -> {
			Objetivo objetivo = getObjetivoCmd.getById(obj.getIdObjetivo());
			entity.setObjetivo(objetivo);
		});
		entity.setUsuarioAlteracao(to.getUsuarioAlteracao());

		return entity;
	}

	public IndicadoresTO buildTO(Indicadores dto) {
		IndicadoresTO to = new IndicadoresTO();
		
		if(Objects.isNull(dto)) {
			return to;
		}

		to.setIdIndicador(dto.getIdIndicador());
		to.setDataInicio(dto.getDataInicio());
		to.setDataFim(dto.getDataFim());
		to.setNome(dto.getNome());
		to.setObjetivo(objetivoTOBuilder.buildTO(dto.getObjetivo()));
		to.setUsuarioAlteracao(dto.getUsuarioAlteracao());

		return to;
	}

	public List<IndicadoresTO> buildAll(List<Indicadores> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
