package br.com.crux.builder;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.crux.dao.dto.PerfilAcessoUsuarioDTO;
import br.com.crux.to.PerfilAcessoUsuarioTO;


@Component
public class PerfilAcessoUsuarioTOBuilder {
	
	public PerfilAcessoUsuarioTO buildTO(PerfilAcessoUsuarioDTO dto) {
		PerfilAcessoUsuarioTO to = new PerfilAcessoUsuarioTO();
		to.setIdUsuario(dto.getIdUsuario());
		to.setNomeUsuario(dto.getNomeUsuario());
		to.setIdUnidade(dto.getIdUnidade());
		to.setNomeUnidade(dto.getNomeUnidade());
		to.setIdModulo(dto.getIdModulo());
		to.setNomeModulo(dto.getNomeModulo());
		to.setIdGrupoModulo(dto.getIdGrupoModulo());
		to.setNomeGrupoModulo(dto.getNomeGrupoModulo());
		to.setDescricaoGrupoModulo(dto.getDescricaoGrupoModulo());
		to.setIdUsuarioGrupo(dto.getIdUsuarioGrupo());
		
		return to;
	}
	
	public List<PerfilAcessoUsuarioTO> buildAll(List<PerfilAcessoUsuarioDTO> dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
