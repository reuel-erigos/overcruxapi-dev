package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.entity.Objetivo;
import br.com.crux.to.ObjetivoTO;

@Component
public class ObjetivoTOBuilder {

	@Autowired private PerspectivaTOBuilder perspectivaTOBuilder;

	public Objetivo build(ObjetivoTO to) {
		Objetivo entity = new Objetivo();

		entity.setIdObjetivo(to.getIdObjetivo());
		entity.setDataImplantacao(to.getDataImplantacao());
		entity.setDataTermino(to.getDataTermino());
		entity.setNome(to.getNome());
		entity.setPerspectiva(perspectivaTOBuilder.build(to.getPerspectiva()));
		entity.setUsuarioAlteracao(to.getUsuarioAlteracao());

		return entity;
	}

	public ObjetivoTO buildTO(Objetivo dto) {
		ObjetivoTO to = new ObjetivoTO();

		if (Objects.isNull(dto)) {
			return to;
		}

		to.setIdObjetivo(dto.getIdObjetivo());
		to.setDataImplantacao(dto.getDataImplantacao());
		to.setDataTermino(dto.getDataTermino());
		to.setNome(dto.getNome());
		to.setPerspectiva(perspectivaTOBuilder.buildTO(dto.getPerspectiva()));
		to.setUsuarioAlteracao(dto.getUsuarioAlteracao());

		return to;
	}

	public List<ObjetivoTO> buildAll(List<Objetivo> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
