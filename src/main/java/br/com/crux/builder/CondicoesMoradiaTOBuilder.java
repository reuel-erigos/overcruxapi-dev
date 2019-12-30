package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.crux.entity.CondicoesMoradia;
import br.com.crux.to.CondicoesMoradiaTO;

@Component
public class CondicoesMoradiaTOBuilder {

	public CondicoesMoradia build(CondicoesMoradiaTO p) {
		CondicoesMoradia retorno = new CondicoesMoradia();
		
		retorno.setId(p.getId());
		retorno.setDescricao(p.getDescricao());
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public CondicoesMoradiaTO buildTO(CondicoesMoradia p) {
		CondicoesMoradiaTO retorno = new CondicoesMoradiaTO();

		if (Objects.isNull(p)) {
			return retorno;
		}

		retorno.setId(p.getId());
		retorno.setDescricao(p.getDescricao());
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public List<CondicoesMoradiaTO> buildAll(List<CondicoesMoradia> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
