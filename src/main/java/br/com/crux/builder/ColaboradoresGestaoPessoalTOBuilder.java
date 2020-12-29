package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.crux.dao.dto.ColaboradoresGestaoPessoalDTO;
import br.com.crux.to.ColaboradoresGestaoPessoalTO;

@Component
public class ColaboradoresGestaoPessoalTOBuilder {

	public ColaboradoresGestaoPessoalTO buildComboTO(ColaboradoresGestaoPessoalDTO p) {
		ColaboradoresGestaoPessoalTO retorno = new ColaboradoresGestaoPessoalTO();
		
		if(Objects.isNull(p)) {
			return retorno;
		}
		
		BeanUtils.copyProperties(p, retorno);
		
		return retorno;
	}

	public List<ColaboradoresGestaoPessoalTO> buildAllDTO(List<ColaboradoresGestaoPessoalDTO> dtos) {
		return dtos.stream().map(dto -> buildComboTO(dto)).collect(Collectors.toList());
	}
	
}

