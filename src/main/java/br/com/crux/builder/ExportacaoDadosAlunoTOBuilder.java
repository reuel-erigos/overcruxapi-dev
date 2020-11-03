package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.crux.dao.dto.ExportacaoDadosAlunoDTO;
import br.com.crux.to.ExportacaoDadosAlunoTO;

@Component
public class ExportacaoDadosAlunoTOBuilder {

	public ExportacaoDadosAlunoTO buildComboTO(ExportacaoDadosAlunoDTO p) {
		ExportacaoDadosAlunoTO retorno = new ExportacaoDadosAlunoTO();
		
		if(Objects.isNull(p)) {
			return retorno;
		}
		
		BeanUtils.copyProperties(p, retorno);
		
		return retorno;
	}

	public List<ExportacaoDadosAlunoTO> buildAllDTO(List<ExportacaoDadosAlunoDTO> dtos) {
		return dtos.stream().map(dto -> buildComboTO(dto)).collect(Collectors.toList());
	}
	
}

