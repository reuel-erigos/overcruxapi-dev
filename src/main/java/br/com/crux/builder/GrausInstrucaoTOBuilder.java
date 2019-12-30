package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.crux.entity.GrausInstrucao;
import br.com.crux.to.GrausInstrucaoTO;

@Component
public class GrausInstrucaoTOBuilder {


	public GrausInstrucao build(GrausInstrucaoTO p) {
		GrausInstrucao retorno = new GrausInstrucao();
		
		retorno.setId(p.getId());
		retorno.setDescricao(p.getDescricao());
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public GrausInstrucaoTO buildTO(GrausInstrucao p) {
		GrausInstrucaoTO retorno = new GrausInstrucaoTO();
		
		if(Objects.isNull(p)) {
			return retorno;
		}
		retorno.setId(p.getId());
		retorno.setDescricao(p.getDescricao());
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		return retorno;
	}

	public List<GrausInstrucaoTO> buildAll(List<GrausInstrucao> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
