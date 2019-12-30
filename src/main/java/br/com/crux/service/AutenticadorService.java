package br.com.crux.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.AutenticadorCmd;
import br.com.crux.to.LoginTO;
import br.com.crux.to.TrocaSenhaTO;
import br.com.crux.to.UsuarioLogadoTO;

@RestController
@RequestMapping(value = "autenticador")
public class AutenticadorService {

	@Autowired
	private AutenticadorCmd autenticadorLoginCmd;

	@PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public UsuarioLogadoTO login(@RequestBody LoginTO user) {
		return autenticadorLoginCmd.autenticar(user);
	}
	
	@PostMapping(path = "/trocar-senha", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void trocarSenha(@RequestBody TrocaSenhaTO trocaSenhaTO) {
		autenticadorLoginCmd.trocarSenha(trocaSenhaTO);
	}
	
}
