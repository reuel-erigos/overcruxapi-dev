package br.com.crux.builder.relatorios;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.crux.to.relatorios.gestao_pessoal.AniversariantesDTO;
import br.com.crux.to.relatorios.gestao_pessoal.AniversariantesTO;

@Component
public class AniversariantesTOBuilder {

	public AniversariantesTO buildTO(AniversariantesDTO dto) {
		AniversariantesTO retorno = new AniversariantesTO();
		BeanUtils.copyProperties(dto, retorno);
		return retorno;
	}

	public List<AniversariantesTO> buildAll(List<AniversariantesDTO> dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
