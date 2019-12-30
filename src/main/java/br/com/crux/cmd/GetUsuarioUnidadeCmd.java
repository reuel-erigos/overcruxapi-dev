package br.com.crux.cmd;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.UsuariosUnidadeTOBuilder;
import br.com.crux.dao.repository.UsuariosUnidadeRepository;
import br.com.crux.entity.UsuariosSistema;
import br.com.crux.entity.UsuariosUnidade;
import br.com.crux.to.UsuariosUnidadesTO;

@Component
public class GetUsuarioUnidadeCmd {

	@Autowired private UsuariosUnidadeRepository usuariosUnidadeRepository;
	@Autowired private GetUsuarioSistemaCmd getUsuarioSistemaCmd;
	@Autowired private UsuariosUnidadeTOBuilder usuariosUnidadeTOBuilder;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	
	public List<UsuariosUnidadesTO> getUnidadesUsuarioLogadoComAcesso() {
		return getUnidadesComAcesso(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
	}
	
	public List<UsuariosUnidadesTO> getUnidadesComAcesso(Long idUsuario) {
		UsuariosSistema usuario = getUsuarioSistemaCmd.getById(idUsuario);
		List<UsuariosUnidade> unidades = usuariosUnidadeRepository.findByUsuarioSistema(usuario.getIdUsuario()).orElse(Collections.emptyList());
		return usuariosUnidadeTOBuilder.buildAll(unidades);
	}

}
