package br.com.crux.builder;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.crux.dao.dto.AcessoDTO;
import br.com.crux.to.AcessoTO;


@Component
public class AcessoTOBuilder {
	
	public AcessoTO buildTO(AcessoDTO dto) {
		AcessoTO to = new AcessoTO();
		
		to.setIdModulo(dto.getIdModulo());
		to.setNomeModulo(dto.getNomeModulo());
		to.setConsulta(dto.getConsulta());
		to.setAltera(dto.getAltera());
		to.setInsere(dto.getInsere());
		to.setDeleta(dto.getDeleta());
		
		return to;
	}
	
	public List<AcessoTO> buildAll(List<AcessoDTO> dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
