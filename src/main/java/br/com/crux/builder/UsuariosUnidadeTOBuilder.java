package br.com.crux.builder;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.entity.UsuariosUnidade;
import br.com.crux.to.UsuariosUnidadesTO;


@Component
public class UsuariosUnidadeTOBuilder {
	
	@Autowired private UnidadeTOBuilder unidadeTOBuilder;
	@Autowired private UsuariosSistemaTOBuilder usuariosSistemaTOBuilder;
	
	

	public UsuariosUnidade build(UsuariosUnidadesTO p) {
		UsuariosUnidade retorno = new UsuariosUnidade();

		retorno.setId(p.getId());
		retorno.setUnidade(unidadeTOBuilder.build(p.getUnidade()));
		retorno.setUsuarioSistema(usuariosSistemaTOBuilder.build(p.getUsuarioSistema()));
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());
		
		return retorno;
	}

	public UsuariosUnidadesTO buildTO(UsuariosUnidade p) {
		UsuariosUnidadesTO retorno = new UsuariosUnidadesTO();
		
		retorno.setId(p.getId());
		retorno.setUnidade(unidadeTOBuilder.buildTO(p.getUnidade()));
		retorno.setUsuarioSistema(usuariosSistemaTOBuilder.buildTOPSemUnidades(p.getUsuarioSistema()));
		retorno.setUsuarioAlteracao(p.getUsuarioAlteracao());

		
		return retorno;
	}

	public List<UsuariosUnidadesTO> buildAll(List<UsuariosUnidade> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}
	
	public List<UsuariosUnidade> buildAllTO(List<UsuariosUnidadesTO> dtos) {
		return dtos.stream().map(dto -> build(dto)).collect(Collectors.toList());
	}
}
