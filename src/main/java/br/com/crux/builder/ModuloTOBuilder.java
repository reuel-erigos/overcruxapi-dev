package br.com.crux.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.crux.dao.dto.ModuloDTO;
import br.com.crux.entity.Modulo;
import br.com.crux.to.ModuloTO;


@Component
public class ModuloTOBuilder {

	public ModuloTO buildTO(Modulo p) {
		ModuloTO to = new ModuloTO();
		
		to.setId(p.getId());
		to.setDescricao(p.getDescricao());
		to.setNome(p.getNome());
		
		if(Objects.nonNull(p.getModuloPai())) {
			to.setModuloPai(buildTO(p.getModuloPai()));
		}
		
		to.setUsuarioAlteracao(p.getUsuarioAlteracao());
		return to;
	}

	public Modulo build(ModuloTO p) {
		Modulo to = new Modulo();
		
		to.setId(p.getId());
		to.setDescricao(p.getDescricao());
		to.setNome(p.getNome());
		
		if(Objects.nonNull(p.getModuloPai())) {
			to.setModuloPai(build(p.getModuloPai()));
		}
		
		to.setUsuarioAlteracao(p.getUsuarioAlteracao());
		return to;
	}
	
	public List<ModuloTO> buildAll(List<Modulo> dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}
	
	
	public ModuloTO buildDTO(ModuloDTO dto) {
		ModuloTO to = new ModuloTO();
		to.setId(dto.getIdModulo());
		to.setDescricao(dto.getDescricao());
		to.setNome(dto.getNome());
		
		return to;
	}
	
	public List<ModuloTO> buildAllDTO(List<ModuloDTO> dtos){
		return dtos.stream().map(dto -> buildDTO(dto)).collect(Collectors.toList());
	}

}
