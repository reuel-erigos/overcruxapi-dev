package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.UsuariosSistemaTOBuilder;
import br.com.crux.dao.GetUsuarioSistemaDao;
import br.com.crux.dao.repository.UsuarioSistemaRepository;
import br.com.crux.entity.UsuariosSistema;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.UsuarioSistemaNaoEncontradoRule;
import br.com.crux.to.UsuariosSistemaTO;

@Component
public class GetUsuarioSistemaCmd {
	
	@Autowired private UsuarioSistemaRepository repository;
	@Autowired private UsuariosSistemaTOBuilder toBuilder;
	
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	
	@Autowired private GetUsuarioSistemaDao getUsuarioSistemaDao;
	@Autowired private UsuarioSistemaNaoEncontradoRule usuarioSistemaNaoEncontradoRule;
	
	public UsuariosSistema get(String username) {
		Optional<UsuariosSistema> usuarioSistema = getUsuarioSistemaDao.getUsuarioByUsername(username);
		usuarioSistemaNaoEncontradoRule.verificar(usuarioSistema);
		return usuarioSistema.get();
	}
	
	
	public List<UsuariosSistemaTO> getAll() {
		Optional<List<UsuariosSistema>> entitys = repository.findByUnidade(getUnidadeLogadaCmd.get().getId());
		if(entitys.isPresent()) {
			return toBuilder.buildAll(entitys.get());
		}
		return new ArrayList<UsuariosSistemaTO>();
	}
	
	public UsuariosSistemaTO getTOById(Long id) {
		UsuariosSistema entity = repository.findById(id).orElseThrow(() ->  new NotFoundException("Usuário não encontrado."));
		return toBuilder.buildTO(entity);
	}	

	public UsuariosSistema getById(Long id) {
		return repository.findById(id).orElseGet(null);
	}	
}
