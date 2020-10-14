package br.com.crux.builder;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.crux.dao.dto.ProvisionamentoDTO;
import br.com.crux.entity.Provisoes;
import br.com.crux.to.ProvisionamentoTO;


@Component
public class ProvisionamentoTOBuilder {
	
	public ProvisionamentoTO buildTO(ProvisionamentoDTO dto) {
		ProvisionamentoTO retorno = new ProvisionamentoTO();
		BeanUtils.copyProperties(dto, retorno);
		return retorno;
	}

	public ProvisionamentoTO build(Provisoes dto) {
		ProvisionamentoTO retorno = new ProvisionamentoTO();
		BeanUtils.copyProperties(dto, retorno);
		return retorno;
	}

	
	public List<ProvisionamentoTO> buildAll(List<ProvisionamentoDTO> dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

	public List<ProvisionamentoTO> buildTOAll(List<Provisoes> dtos){
		return dtos.stream().map(dto -> build(dto)).collect(Collectors.toList());
	}
}
