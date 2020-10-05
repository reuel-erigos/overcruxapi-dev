package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.crux.entity.view.RateiosProgramaProjeto;
import br.com.crux.to.RateiosProgramaProjetoTO;

@Component
public class RateiosProgramaProjetoTOBuilder {
	
	public RateiosProgramaProjeto build(RateiosProgramaProjetoTO param) {
		RateiosProgramaProjeto retorno = new RateiosProgramaProjeto();
		BeanUtils.copyProperties(param, retorno);
		return retorno;
	}

	public RateiosProgramaProjetoTO buildTO(RateiosProgramaProjeto param) {
		RateiosProgramaProjetoTO retorno = new RateiosProgramaProjetoTO();
		if (Objects.isNull(param)) {
			return retorno;
		}
		BeanUtils.copyProperties(param, retorno);
		return retorno;
	}

	public List<RateiosProgramaProjetoTO> buildAll(List<RateiosProgramaProjeto> dtos) {
		return dtos.stream()
				.map(this::buildTO)
				.collect(Collectors.toList());
	}

}
