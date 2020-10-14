package br.com.crux.builder;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.crux.dao.dto.ConciliacaoDTO;
import br.com.crux.entity.ConciliacaoBancaria;
import br.com.crux.to.ConciliacaoTO;


@Component
public class ConciliacaoTOBuilder {
	
	public ConciliacaoTO buildTO(ConciliacaoDTO dto) {
		ConciliacaoTO retorno = new ConciliacaoTO();
		BeanUtils.copyProperties(dto, retorno);
		return retorno;
	}

	public ConciliacaoTO build(ConciliacaoBancaria dto) {
		ConciliacaoTO retorno = new ConciliacaoTO();
		BeanUtils.copyProperties(dto, retorno);
		return retorno;
	}

	
	public List<ConciliacaoTO> buildAll(List<ConciliacaoDTO> dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

	public List<ConciliacaoTO> buildTOAll(List<ConciliacaoBancaria> dtos){
		return dtos.stream().map(dto -> build(dto)).collect(Collectors.toList());
	}
}
