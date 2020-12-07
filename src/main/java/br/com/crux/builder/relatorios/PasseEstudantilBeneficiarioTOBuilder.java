package br.com.crux.builder.relatorios;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.crux.to.relatorios.beneficiarios.PasseEstudantilBeneficiarioDTO;
import br.com.crux.to.relatorios.beneficiarios.PasseEstudantilBeneficiarioTO;

@Component
public class PasseEstudantilBeneficiarioTOBuilder {

	public PasseEstudantilBeneficiarioTO buildTO(PasseEstudantilBeneficiarioDTO dto) {
		PasseEstudantilBeneficiarioTO retorno = new PasseEstudantilBeneficiarioTO();
		BeanUtils.copyProperties(dto, retorno);
		return retorno;
	}

	public List<PasseEstudantilBeneficiarioTO> buildAll(List<PasseEstudantilBeneficiarioDTO> dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
