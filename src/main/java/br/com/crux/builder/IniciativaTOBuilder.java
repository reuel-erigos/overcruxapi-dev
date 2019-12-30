package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.entity.Iniciativa;
import br.com.crux.to.IniciativaTO;


@Component
public class IniciativaTOBuilder {
	
	@Autowired
	private MetasTOBuilder metasTOBuilder;
	

	public Iniciativa build(IniciativaTO param) {
		Iniciativa retorno = new Iniciativa();
		
		retorno.setId(param.getId());
		retorno.setDataInicio(param.getDataInicio());
		retorno.setDataFim(param.getDataFim());
		retorno.setNome(param.getNome());
		retorno.setMeta(metasTOBuilder.build(param.getMetas()));
		retorno.setUsuarioAlteracao(param.getUsuarioAlteracao());
		
		return retorno;
	}

	
	public IniciativaTO buildTO(Iniciativa param) {
		IniciativaTO retorno = new IniciativaTO();
		
		if(Objects.isNull(param)) {
			return retorno;
		}
		
		retorno.setId(param.getId());
		retorno.setDataInicio(param.getDataInicio());
		retorno.setDataFim(param.getDataFim());
		retorno.setNome(param.getNome());
		retorno.setMetas(metasTOBuilder.buildTO(param.getMeta()));
		retorno.setUsuarioAlteracao(param.getUsuarioAlteracao());

		
		return retorno;
	}
	
	
	
	public List<IniciativaTO> buildAll(List<Iniciativa> dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
