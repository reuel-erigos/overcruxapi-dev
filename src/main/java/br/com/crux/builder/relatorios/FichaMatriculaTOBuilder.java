package br.com.crux.builder.relatorios;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.crux.to.relatorios.FichaMatriculaDTO;
import br.com.crux.to.relatorios.FichaMatriculaTO;

@Component
public class FichaMatriculaTOBuilder {

	public FichaMatriculaTO buildTO(FichaMatriculaDTO dto) {
		FichaMatriculaTO retorno = new FichaMatriculaTO();
		BeanUtils.copyProperties(dto, retorno);
		return retorno;
	}

	public List<FichaMatriculaTO> buildAll(List<FichaMatriculaDTO> dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
