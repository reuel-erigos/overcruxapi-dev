package br.com.crux.cmd;

import java.util.Objects;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.crux.exception.NotFoundException;
import br.com.crux.security.UsuarioLocals;
import br.com.crux.to.UsuarioLogadoTO;

@Component
public class GetUsuarioLogadoCmd {

	
	public Authentication getAuthentication() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(Objects.isNull(authentication)) {
			throw new NotFoundException("Problema ao recuperar o usuário autenticado.");
		}
		return authentication;
	}
	
	public UsuarioLogadoTO getUsuarioLogado() {
		Authentication authentication = getAuthentication();
		String username = authentication.getName();
		
		UsuarioLogadoTO usuarioLogado = (UsuarioLogadoTO) UsuarioLocals.get(username);
		if(usuarioLogado == null) {
			throw new NotFoundException("Erro ao recuperar o usuário logado.");
		}

		return usuarioLogado;
	}
	
}
