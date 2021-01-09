package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.crux.to.relatorios.financeiro.FaturasPagarDTO;
import br.com.crux.to.relatorios.financeiro.FaturasPagarTO;

@Component
public class FaturasPagarTOBuilder {

	public FaturasPagarTO buildComboTO(FaturasPagarDTO p) {
		FaturasPagarTO retorno = new FaturasPagarTO();
		
		if(Objects.isNull(p)) {
			return retorno;
		}
		
		BeanUtils.copyProperties(p, retorno);
		
		return retorno;
	}

	public List<FaturasPagarTO> buildAllDTO(List<FaturasPagarDTO> dtos) {
		return dtos.stream().map(dto -> buildComboTO(dto)).collect(Collectors.toList());
	}
	
}

