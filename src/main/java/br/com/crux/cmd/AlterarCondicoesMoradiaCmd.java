package br.com.crux.cmd;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.CondicoesMoradiaRepository;
import br.com.crux.entity.CondicoesMoradia;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.CondicoesMoradiaTO;
import br.com.crux.to.UsuarioLogadoTO;

@Component
public class AlterarCondicoesMoradiaCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	@Autowired private CondicoesMoradiaRepository repository;

	
	public void alterar(CondicoesMoradiaTO to) {
		Optional<CondicoesMoradia> entityOptional = repository.findById(to.getId());
		if(!entityOptional.isPresent()) {
			throw new NotFoundException("Condição moradia informada não existe.");
		}
		
		CondicoesMoradia entity = entityOptional.get();

		entity.setDescricao(to.getDescricao());
		
		UsuarioLogadoTO usuarioLogado = getUsuarioLogadoCmd.getUsuarioLogado();
		entity.setUsuarioAlteracao(usuarioLogado.getIdUsuario());
		
		repository.save(entity);
		
	}
}
