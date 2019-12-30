package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.crux.entity.Cbo;
import br.com.crux.to.CboTO;

@Component
public class CboTOBuilder {


	public Cbo build(CboTO param) {
		Cbo retorno = new Cbo();

		retorno.setId(param.getId());
		retorno.setDescricaoTipo(param.getDescricaoTipo());
		retorno.setNomeTitulo(param.getNomeTitulo());
		retorno.setNumero(Long.valueOf(param.getNumero().replace("-", "")));
		retorno.setUsuarioAlteracao(param.getUsuarioAlteracao());

		return retorno;
	}

	public CboTO buildTO(Cbo param) {
		CboTO retorno = new CboTO();
		
		if(Objects.isNull(param)) {
			return retorno;
		}

		retorno.setId(param.getId());
		retorno.setDescricaoTipo(param.getDescricaoTipo());
		retorno.setNomeTitulo(param.getNomeTitulo());
		retorno.setNumero(String.valueOf(param.getNumero()));
		retorno.setUsuarioAlteracao(param.getUsuarioAlteracao());

		return retorno;
	}

	public List<CboTO> buildAll(List<Cbo> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
