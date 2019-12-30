package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.SolucoesRepository;
import br.com.crux.entity.Solucoes;
import br.com.crux.rule.CamposObrigatoriosSolucoesRule;
import br.com.crux.to.SolucoesTO;
import br.com.crux.to.UsuarioLogadoTO;

@Component
public class CadastrarSolucoesCmd {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	
	@Autowired private SolucoesRepository repository;
	@Autowired private CamposObrigatoriosSolucoesRule camposObrigatoriosRule;
	
	
	public void cadastrar(SolucoesTO to) {
		
		camposObrigatoriosRule.verificar(to.getDescricao());
		
		Solucoes entity = new Solucoes();

		entity.setDescricao(to.getDescricao());
		
		UsuarioLogadoTO usuarioLogado = getUsuarioLogadoCmd.getUsuarioLogado();
		entity.setUsuarioAlteracao(usuarioLogado.getIdUsuario());
		
		repository.save(entity);
		
	}
}
