package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.crux.to.relatorios.financeiro.SaldoProjetoDTO;
import br.com.crux.to.relatorios.financeiro.SaldoProjetoTO;

@Component
public class SaldoProjetoTOBuilder {

	public SaldoProjetoTO buildComboTO(SaldoProjetoDTO p) {
		SaldoProjetoTO retorno = new SaldoProjetoTO();
		
		if(Objects.isNull(p)) {
			return retorno;
		}
		
		BeanUtils.copyProperties(p, retorno);
		
		return retorno;
	}

	public List<SaldoProjetoTO> buildAllDTO(List<SaldoProjetoDTO> dtos) {
		return dtos.stream().map(dto -> buildComboTO(dto)).collect(Collectors.toList());
	}
	
}

