package br.com.crux.builder.relatorios;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.crux.to.relatorios.gestao_pessoal.FuncaoDTO;
import br.com.crux.to.relatorios.gestao_pessoal.FuncaoTO;

@Component
public class FuncaoTOBuilder {

	public FuncaoTO buildTO(FuncaoDTO dto) {
		FuncaoTO retorno = new FuncaoTO();
		BeanUtils.copyProperties(dto, retorno);
		return retorno;
	}

	public List<FuncaoTO> buildAll(List<FuncaoDTO> dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
