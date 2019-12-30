package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.CondicoesMoradiaRepository;
import br.com.crux.entity.CondicoesMoradia;
import br.com.crux.to.CondicoesMoradiaTO;
import br.com.crux.to.UsuarioLogadoTO;

@Component
public class CadastrarCondicoesMoradiaCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	@Autowired private CondicoesMoradiaRepository repository;
	
	
	public void cadastrar(CondicoesMoradiaTO to) {
		CondicoesMoradia entity = new CondicoesMoradia();

		entity.setDescricao(to.getDescricao());
		
		UsuarioLogadoTO usuarioLogado = getUsuarioLogadoCmd.getUsuarioLogado();
		entity.setUsuarioAlteracao(usuarioLogado.getIdUsuario());
		
		repository.save(entity);
		
	}
}
