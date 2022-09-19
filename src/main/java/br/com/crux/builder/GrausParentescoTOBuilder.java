package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.crux.entity.GrausParentesco;
import br.com.crux.to.GrausParentescoTO;

@Component
public class GrausParentescoTOBuilder {


	public GrausParentesco build(GrausParentescoTO p) {
		GrausParentesco retorno = new GrausParentesco();
		
		retorno.setId(p.getId());
		retorno.setNome(p.getNome());

		return retorno;
	}

	public GrausParentescoTO buildTO(GrausParentesco p) {
		GrausParentescoTO retorno = new GrausParentescoTO();
		
		if(Objects.isNull(p)) {
			return retorno;
		}
		retorno.setId(p.getId());
		retorno.setNome(p.getNome());
		return retorno;
	}

	public List<GrausParentescoTO> buildAll(List<GrausParentesco> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
