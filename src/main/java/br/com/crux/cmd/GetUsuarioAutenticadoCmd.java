package br.com.crux.cmd;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.UsuarioSistemaRepository;
import br.com.crux.dao.repository.UsuariosGrupoRepository;
import br.com.crux.entity.UsuariosSistema;
import br.com.crux.exception.UsuarioSemAcessoException;


@Component
public class GetUsuarioAutenticadoCmd implements UserDetailsService{
	
	@Autowired private UsuarioSistemaRepository usuarioSistemaRepository;
	@Autowired private UsuariosGrupoRepository usuariosGrupoRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UsuariosSistema> result = usuarioSistemaRepository.findByUsername(username);
		
		if(!result.isPresent()) throw new UsernameNotFoundException("Usuário não existe.");
		
		UsuariosSistema user = result.get();
		
		if (user.getStAtivo() == Boolean.FALSE 
			|| 
			Objects.nonNull(user.getDataFimVigencia()) && user.getDataFimVigencia().isAfter(LocalDateTime.now()) ) {
			
			throw new UsuarioSemAcessoException("Usuário inativo.");
		}
		
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		user.getUsuariosUnidades().stream()
		                          .filter(p -> usuariosGrupoRepository.getPermissoesNaUnidade(user.getIdUsuario(), p.getUnidade().getIdUnidade()).isPresent())
		                          .forEach(unidades -> roles.add(new SimpleGrantedAuthority("ROLE_" + unidades.getUnidade().getSiglaUnidade().toUpperCase())));
		
		List<GrantedAuthority> authorities = roles;

		
		User userSpring = new User(user.getUsername(), user.getSenha(), authorities);
		
		return userSpring;
	}
	

	public UsuariosSistema loadUserById(Long id) throws UsernameNotFoundException {
		Optional<UsuariosSistema> result = usuarioSistemaRepository.findById(id);
		
		if(!result.isPresent()) throw new UsernameNotFoundException("Não existe usuário com o id = " + id);
		
		UsuariosSistema user = result.get();
		
		return user;
	}

}
