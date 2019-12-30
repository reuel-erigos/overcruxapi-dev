package br.com.crux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.GetUsuariosPorUnidadeCmd;
import br.com.crux.to.DadosUsuarioUnidadeTO;

@RestController
@RequestMapping(value = "usuario")
public class UsuarioService {

	@Autowired GetUsuariosPorUnidadeCmd getUsuariosPorUnidadeCmd;
	
	@GetMapping(path = "/unidade", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DadosUsuarioUnidadeTO> getUsuariosPorUnidadeLogada() {
		return getUsuariosPorUnidadeCmd.getUsuariosPorUnidade();
	}
	
	@GetMapping(path = "/unidade/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DadosUsuarioUnidadeTO> getUsuariosPorUnidade(@PathVariable(name = "id") Long idUnidade) {
		return getUsuariosPorUnidadeCmd.getUsuariosPorUnidade(idUnidade);
	}
	
}
