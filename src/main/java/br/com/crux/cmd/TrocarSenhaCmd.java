package br.com.crux.cmd;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.crux.dao.AcessoDao;
import br.com.crux.entity.UsuariosSistema;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.ValidarTrocaSenhaRule;
import br.com.crux.security.CustomPasswordEncoder;
import br.com.crux.to.TrocaSenhaTO;

@Component
public class TrocarSenhaCmd {
	
	@Autowired private CustomPasswordEncoder customPasswordEncoder;
	@Autowired private AcessoDao acessoDao;
	@Autowired private ValidarTrocaSenhaRule validarTrocaSenhaRule;
	@Autowired private GetUsuarioSistemaCmd getUsuarioSistemaCmd;
	
	public void trocarSenha(TrocaSenhaTO trocaSenhaTO) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(Objects.isNull(authentication)) {
			throw new NotFoundException("Problema ao recuperar o usuário logado.");
		}
		UsuariosSistema usuariosSistema = getUsuarioSistemaCmd.get(authentication.getName());
		
		validarTrocaSenhaRule.validar(usuariosSistema.getSenha(), trocaSenhaTO.getSenhaAtual(), trocaSenhaTO.getSenhaNova());
		
		String senhaEncode = customPasswordEncoder.encode(trocaSenhaTO.getSenhaNova());
		acessoDao.trocarSenha(authentication.getName(), senhaEncode);
		
		SecurityContextHolder.getContext().setAuthentication(null);
	}

}
