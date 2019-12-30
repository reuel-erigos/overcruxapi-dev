package br.com.crux.cmd;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.SolucoesRepository;
import br.com.crux.entity.Solucoes;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosSolucoesRule;
import br.com.crux.to.SolucoesTO;
import br.com.crux.to.UsuarioLogadoTO;

@Component
public class AlterarSolucoesCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	
	@Autowired private SolucoesRepository repository;
	@Autowired private CamposObrigatoriosSolucoesRule camposObrigatoriosRule;
	
	
	
	public void alterar(SolucoesTO to) {
		Optional<Solucoes> entityOptional = repository.findById(to.getId());
		if(!entityOptional.isPresent()) {
			throw new NotFoundException("Solução informada não existe.");
		}
		
		camposObrigatoriosRule.verificar(to.getDescricao());
		
		Solucoes entity = entityOptional.get();

		entity.setDescricao(to.getDescricao());
		
		UsuarioLogadoTO usuarioLogado = getUsuarioLogadoCmd.getUsuarioLogado();
		entity.setUsuarioAlteracao(usuarioLogado.getIdUsuario());
		
		repository.save(entity);
		
	}
}
