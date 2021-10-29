package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.crux.to.relatorios.financeiro.MovimentacaoContabilDTO;
import br.com.crux.to.relatorios.financeiro.MovimentacaoContabilTO;

@Component
public class MovimentacaoContabilTOBuilder {

	public MovimentacaoContabilTO buildComboTO(MovimentacaoContabilDTO p) {
		MovimentacaoContabilTO retorno = new MovimentacaoContabilTO();
		
		if(Objects.isNull(p)) {
			return retorno;
		}
		
		BeanUtils.copyProperties(p, retorno);
		
		return retorno;
	}

	public List<MovimentacaoContabilTO> buildAllDTO(List<MovimentacaoContabilDTO> dtos) {
		return dtos.stream().map(dto -> buildComboTO(dto)).collect(Collectors.toList());
	}
	
}

