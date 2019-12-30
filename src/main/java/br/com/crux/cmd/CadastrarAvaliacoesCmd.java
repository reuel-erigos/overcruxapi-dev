package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.AvaliacoesRepository;
import br.com.crux.entity.Avaliacoes;
import br.com.crux.to.AvaliacoesTO;
import br.com.crux.to.UsuarioLogadoTO;

@Component
public class CadastrarAvaliacoesCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	@Autowired private AvaliacoesRepository repository;
	
	
	public void cadastrar(AvaliacoesTO to) {
		
		Avaliacoes entity = new Avaliacoes();

		entity.setNome(to.getNome());
		entity.setDescricao(to.getDescricao());
		entity.setDataInicio(to.getDataInicio());
		entity.setDataFim(to.getDataFim());
		
		UsuarioLogadoTO usuarioLogado = getUsuarioLogadoCmd.getUsuarioLogado();
		entity.setUsuarioAlteracao(usuarioLogado.getIdUsuario());
		
		repository.save(entity);
		
	}
}
