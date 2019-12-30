package br.com.crux.builder;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.crux.dao.dto.UnidadeModuloAcessoDTO;
import br.com.crux.to.UnidadeModuloAcessoTO;


@Component
public class UnidadeModuloAcessoTOBuilder {
	
	public UnidadeModuloAcessoTO buildTO(UnidadeModuloAcessoDTO dto) {
		UnidadeModuloAcessoTO to = new UnidadeModuloAcessoTO();
		to.setIdUnidade(dto.getIdUnidade());
		to.setNomeUnidade(dto.getNomeUnidade());
		
		return to;
	}
	
	public List<UnidadeModuloAcessoTO> buildAll(List<UnidadeModuloAcessoDTO> dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}
	
}
