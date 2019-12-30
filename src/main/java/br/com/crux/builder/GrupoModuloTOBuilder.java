package br.com.crux.builder;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.entity.GruposModulo;
import br.com.crux.to.GrupoModuloTO;


@Component
public class GrupoModuloTOBuilder {
	
	@Autowired private UnidadeTOBuilder unidadeTOBuilder;
	@Autowired private PerfilAcessoTOBuilder perfilAcessoTOBuilder;
	@Autowired private ModuloTOBuilder moduloTOBuilder;
	
	public GrupoModuloTO buildTO(GruposModulo p) {
		GrupoModuloTO to = new GrupoModuloTO();

		to.setId(p.getId());
		to.setNome(p.getNome());
		to.setDescricao(p.getDescricao());
		to.setModulo(moduloTOBuilder.buildTO(p.getModulo()));
		to.setUnidade(unidadeTOBuilder.buildTO(p.getUnidade()));
		to.setPerfilAcesso(perfilAcessoTOBuilder.buildTO(p.getPerfilAcesso()));
		to.setUsuarioAlteracao(p.getUsuarioAlteracao());
		
		return to;
	}

	public GruposModulo buildTO(GrupoModuloTO p) {
		GruposModulo to = new GruposModulo();

		to.setId(p.getId());
		to.setNome(p.getNome());
		to.setDescricao(p.getDescricao());
		to.setModulo(moduloTOBuilder.build(p.getModulo()));
		to.setUnidade(unidadeTOBuilder.build(p.getUnidade()));
		to.setPerfilAcesso(perfilAcessoTOBuilder.build(p.getPerfilAcesso()));
		to.setUsuarioAlteracao(p.getUsuarioAlteracao());
		
		return to;
	}
	
	
	public List<GrupoModuloTO> buildAll(List<GruposModulo> dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
