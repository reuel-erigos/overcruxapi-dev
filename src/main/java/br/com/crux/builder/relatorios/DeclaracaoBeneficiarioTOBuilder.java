package br.com.crux.builder.relatorios;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.crux.to.relatorios.beneficiarios.DeclaracaoBeneficiarioDTO;
import br.com.crux.to.relatorios.beneficiarios.DeclaracaoBeneficiarioTO;

@Component
public class DeclaracaoBeneficiarioTOBuilder {

	public DeclaracaoBeneficiarioTO buildTO(DeclaracaoBeneficiarioDTO dto) {
		DeclaracaoBeneficiarioTO retorno = new DeclaracaoBeneficiarioTO();
		BeanUtils.copyProperties(dto, retorno);
		return retorno;
	}

	public List<DeclaracaoBeneficiarioTO> buildAll(List<DeclaracaoBeneficiarioDTO> dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
