package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import br.com.crux.builder.AcessoUnidadeTOBuilder;
import br.com.crux.dao.repository.UnidadeRepository;
import br.com.crux.entity.Unidade;
import br.com.crux.entity.UsuariosSistema;
import br.com.crux.exception.SemAcessoUnidadeException;
import br.com.crux.security.UsuarioLocals;
import br.com.crux.to.AcessoUnidadeTO;
import br.com.crux.to.UsuarioLogadoTO;

@Component
public class SaveUsuarioLogadoCmd {
	
	@Autowired private TokenJwtCmd createTokenJwtCmd;
	@Autowired private UnidadeRepository unidadeRepository;
	@Autowired private AcessoUnidadeTOBuilder unidadeTOBuilder;
	@Autowired private CarregarUnidadeLogadaCmd carregarUnidadeLogadaCmd;
	@Autowired private GetUsuarioSistemaCmd getUsuarioSistemaCmd;
	
	public void reset(String username) {
		UsuarioLocals.set(username, null);
	}
	
	@Transactional
	public UsuarioLogadoTO save(Authentication auth) {
		String username = auth.getName();
		return montarUsuarioLogado(username, auth.getAuthorities());
	}
	
	
	private UsuarioLogadoTO montarUsuarioLogado(String username, Collection<? extends GrantedAuthority> authorities) {
		String jwt = createTokenJwtCmd.createToken(username, authorities);
		
		UsuarioLogadoTO usuarioLogadoTO = UsuarioLocals.get(username);
		if(usuarioLogadoTO == null) {
			usuarioLogadoTO = new UsuarioLogadoTO();
		}
		
		UsuariosSistema usuariosSistema = getUsuarioSistemaCmd.get(username);
		usuarioLogadoTO.setIdUsuario(usuariosSistema.getIdUsuario());
		
		usuarioLogadoTO.setToken(jwt);
		usuarioLogadoTO.setNomeUsuario(usuariosSistema.getPessoaFisica().getNome());
		usuarioLogadoTO.setUsername(username);
		usuarioLogadoTO.setTrocarSenha(usuariosSistema.getStTrocaSenha());
		usuarioLogadoTO.setIdPessoaFisica(usuariosSistema.getPessoaFisica().getId());
		
		
		List<AcessoUnidadeTO> unidades = new ArrayList<>();
		authorities.stream().forEach( autho -> {
			String codigoUnidade = autho.getAuthority().replace("ROLE_", "");
			Optional<Unidade> unidade = unidadeRepository.findBySiglaUnidadeIgnoreCase(codigoUnidade);
			
			if(unidade.isPresent()) {
				unidades.add(unidadeTOBuilder.build(unidade.get()));
			}
		});
		usuarioLogadoTO.setUnidades(unidades);
		
		if( usuarioLogadoTO.getUnidades().isEmpty() ) {
			throw new SemAcessoUnidadeException("Usuário não possui acesso em unidades");
		}
		
		
		//Carrega a unidade Logada, se o usuário tiver apenas uma unidade com acesso 
		if(authorities.size() == 1) {
			Long idUnidade = usuarioLogadoTO.getUnidades().get(0).getId();
			AcessoUnidadeTO unidadeLogada = carregarUnidadeLogadaCmd.carregarUnidadeLogada(idUnidade);
			usuarioLogadoTO.setUnidadeLogada(unidadeLogada);
		}
		
		return usuarioLogadoTO;
	}	
	
}
