package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.crux.to.relatorios.financeiro.NormativaPagamentosDTO;
import br.com.crux.to.relatorios.financeiro.NormativaPagamentosTO;

@Component
public class NormativaPagamentosTOBuilder {

	public NormativaPagamentosTO buildComboTO(NormativaPagamentosDTO p) {
		NormativaPagamentosTO retorno = new NormativaPagamentosTO();
		
		if(Objects.isNull(p)) {
			return retorno;
		}
		
		BeanUtils.copyProperties(p, retorno);
		
		return retorno;
	}

	public List<NormativaPagamentosTO> buildAllDTO(List<NormativaPagamentosDTO> dtos) {
		return dtos.stream().map(dto -> buildComboTO(dto)).collect(Collectors.toList());
	}
	
}

