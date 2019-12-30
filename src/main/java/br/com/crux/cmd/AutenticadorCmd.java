package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.crux.exception.UsuarioSemAcessoException;
import br.com.crux.security.UsuarioLocals;
import br.com.crux.to.LoginTO;
import br.com.crux.to.TrocaSenhaTO;
import br.com.crux.to.UsuarioLogadoTO;

@Component
public class AutenticadorCmd {
	
	@Autowired private AuthenticationManager authManager;
	@Autowired private TrocarSenhaCmd trocarSenhaCmd;
	@Autowired private SaveUsuarioLogadoCmd saveUsuarioLogadoCmd;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;    
	@Autowired private TokenJwtCmd createTokenJwtCmd;
	
	
	public UsuarioLogadoTO autenticar(LoginTO user) {
		UsuarioLogadoTO usuarioLogadoTO;
		try {
			UsernamePasswordAuthenticationToken userAuth = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getSenha());
			Authentication auth = authManager.authenticate(userAuth);
			SecurityContextHolder.getContext().setAuthentication(auth);
			
			usuarioLogadoTO = saveUsuarioLogadoCmd.save(auth);
			UsuarioLocals.set(auth.getName(), usuarioLogadoTO);
			
		} catch (BadCredentialsException e) {
			throw new UsuarioSemAcessoException("Usuário/Senha inválidos.");
		}
		
		return usuarioLogadoTO;
	}


	public UsuarioLogadoTO refreshToken() {
		Authentication auth = getUsuarioLogadoCmd.getAuthentication();
		String tokenJwt = createTokenJwtCmd.createToken(auth.getName(), auth.getAuthorities());
		
		UsuarioLogadoTO usuarioLogadoTO = saveUsuarioLogadoCmd.save(auth);
		usuarioLogadoTO.setToken(tokenJwt);

		UsuarioLocals.set(auth.getName(), usuarioLogadoTO);
		return usuarioLogadoTO;
	}
	
	
	public void trocarSenha(TrocaSenhaTO trocaSenhaTO) {
		trocarSenhaCmd.trocarSenha(trocaSenhaTO);
	}

}
