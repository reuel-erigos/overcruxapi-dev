package br.com.crux.cmd;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.AvaliacoesRepository;
import br.com.crux.entity.Avaliacoes;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.AvaliacoesTO;
import br.com.crux.to.UsuarioLogadoTO;

@Component
public class AlterarAvaliacoesCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	@Autowired private AvaliacoesRepository repository;

	
	public void alterar(AvaliacoesTO to) {
		Optional<Avaliacoes> entityOptional = repository.findById(to.getId());
		if(!entityOptional.isPresent()) {
			throw new NotFoundException("Avaliação informada não existe.");
		}
		
		Avaliacoes entity = entityOptional.get();

		entity.setNome(to.getNome());
		entity.setDescricao(to.getDescricao());
		entity.setDataInicio(to.getDataInicio());
		entity.setDataFim(to.getDataFim());
		
		UsuarioLogadoTO usuarioLogado = getUsuarioLogadoCmd.getUsuarioLogado();
		entity.setUsuarioAlteracao(usuarioLogado.getIdUsuario());
		
		repository.save(entity);
		
	}
}
