package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.crux.entity.RegiaoAdministrativa;
import br.com.crux.to.RegiaoAdministrativaTO;

@Component
public class RegiaoAdministrativaTOBuilder {

	public RegiaoAdministrativaTO buildTO(RegiaoAdministrativa entity) {
		RegiaoAdministrativaTO to = new RegiaoAdministrativaTO();
		if (Objects.isNull(entity)) {
			return to;
		}
		
		BeanUtils.copyProperties(entity, to);
		
		return to;
	}

	public RegiaoAdministrativa build(RegiaoAdministrativaTO to) {
		RegiaoAdministrativa entity = new RegiaoAdministrativa();

		BeanUtils.copyProperties(to, entity);
		
		return entity;
	}
	
	public List<RegiaoAdministrativaTO> buildAll(List<RegiaoAdministrativa> lista) {
		return lista.stream()
				.map(this::buildTO)
				.collect(Collectors.toList());
	}

}
