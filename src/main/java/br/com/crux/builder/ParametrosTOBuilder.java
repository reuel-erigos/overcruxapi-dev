package br.com.crux.builder;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.crux.entity.Parametros;
import br.com.crux.to.ParametrosTO;

@Component
public class ParametrosTOBuilder {

	public Parametros build(ParametrosTO p) {
		Parametros retorno = new Parametros();

		retorno.setId(p.getId());
		retorno.setCodigo(p.getCodigo());
		retorno.setDescricao(p.getDescricao());
		retorno.setValor(p.getValor());
		
		return retorno;
	}

	public ParametrosTO buildTO(Parametros p) {
		ParametrosTO retorno = new ParametrosTO();
		
		retorno.setId(p.getId());
		retorno.setCodigo(p.getCodigo());
		retorno.setDescricao(p.getDescricao());
		retorno.setValor(p.getValor());

		return retorno;
	}

	public List<ParametrosTO> buildAll(List<Parametros> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
