package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.crux.entity.SerieEscolar;
import br.com.crux.to.SerieEscolarTO;

@Component
public class SerieEscolarTOBuilder {

	public SerieEscolarTO buildTO(SerieEscolar entity) {
		SerieEscolarTO to = new SerieEscolarTO();
		if (Objects.isNull(entity)) {
			return to;
		}
		
		BeanUtils.copyProperties(entity, to);
		
		return to;
	}

	public SerieEscolar build(SerieEscolarTO to) {
		SerieEscolar entity = new SerieEscolar();

		BeanUtils.copyProperties(to, entity);
		
		return entity;
	}
	
	public List<SerieEscolarTO> buildAll(List<SerieEscolar> lista) {
		return lista.stream()
				.map(this::buildTO)
				.collect(Collectors.toList());
	}

}
