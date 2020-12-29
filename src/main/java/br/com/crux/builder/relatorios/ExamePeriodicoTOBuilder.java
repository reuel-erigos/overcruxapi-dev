package br.com.crux.builder.relatorios;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.crux.to.relatorios.gestao_pessoal.ExamePeriodicoDTO;
import br.com.crux.to.relatorios.gestao_pessoal.ExamePeriodicoTO;

@Component
public class ExamePeriodicoTOBuilder {

	public ExamePeriodicoTO buildTO(ExamePeriodicoDTO dto) {
		ExamePeriodicoTO retorno = new ExamePeriodicoTO();
		BeanUtils.copyProperties(dto, retorno);
		return retorno;
	}

	public List<ExamePeriodicoTO> buildAll(List<ExamePeriodicoDTO> dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
