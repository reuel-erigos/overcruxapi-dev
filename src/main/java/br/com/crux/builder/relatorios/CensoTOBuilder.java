package br.com.crux.builder.relatorios;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.crux.to.relatorios.gestao_pessoal.CensoDTO;
import br.com.crux.to.relatorios.gestao_pessoal.CensoTO;

@Component
public class CensoTOBuilder {

	public CensoTO buildTO(CensoDTO dto) {
		CensoTO retorno = new CensoTO();
		BeanUtils.copyProperties(dto, retorno);
		return retorno;
	}

	public List<CensoTO> buildAll(List<CensoDTO> dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
