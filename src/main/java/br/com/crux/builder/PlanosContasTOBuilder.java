package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.crux.entity.view.PlanosContas;
import br.com.crux.to.PlanosContasTO;

@Component
public class PlanosContasTOBuilder {

	public PlanosContasTO buildTO(PlanosContas dto) {

		PlanosContasTO to = new PlanosContasTO();

		if (Objects.isNull(dto)) {
			return to;
		}

		BeanUtils.copyProperties(dto, to);

		
		return to;
	}

	public List<PlanosContasTO> buildAll(List<PlanosContas> list) {
		return list.stream()
				.map(dto -> buildTO(dto))
				.collect(Collectors.toList());
	}

}
