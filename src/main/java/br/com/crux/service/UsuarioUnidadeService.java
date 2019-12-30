package br.com.crux.service;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.GetUsuarioUnidadeCmd;
import br.com.crux.to.UsuariosUnidadesTO;

@RestController
@RequestMapping(value = "usuariounidade")
public class UsuarioUnidadeService {

	@Autowired
	private GetUsuarioUnidadeCmd getUsuarioUnidadeCmd;
	
	
	@GetMapping(path = "/usuario", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UsuariosUnidadesTO> getAllUnidadesUsuarioTemAcesso() {
		return getUsuarioUnidadeCmd.getUnidadesUsuarioLogadoComAcesso();
	}

	
	@GetMapping(path = "/usuario/{idusuario}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UsuariosUnidadesTO> getAllUnidadesUsuarioTemAcesso(@PathParam(value = "idusuario") Long idUsuario) {
		return getUsuarioUnidadeCmd.getUnidadesComAcesso(idUsuario);
	}
	
}
