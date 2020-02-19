package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.MovimentacoesTOBuilder;
import br.com.crux.dao.repository.MovimentacoesRepository;
import br.com.crux.entity.Movimentacoes;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosMovimentacoesRule;
import br.com.crux.to.MovimentacoesTO;

@Component
public class AlterarMovimentacoesCmd {

	@Autowired private MovimentacoesRepository repository;
	@Autowired private MovimentacoesTOBuilder toBuilder;
	@Autowired private CamposObrigatoriosMovimentacoesRule camposObrigatoriosRule;
	@Autowired private AlterarListaItensMovimentacoesCmd alterarListaItensMovimentacoesCmd;
	@Autowired private AlterarListaFaturasCmd alterarListaFaturasCmd;
	@Autowired private AlterarListaPagamentosFaturaCmd alterarListaPagamentosFaturaCmd;
	

	public void alterar(MovimentacoesTO to) {
		Movimentacoes entity = repository.findById(to.getId())
				.orElseThrow(() -> new NotFoundException("Entidade informada não existe."));

		camposObrigatoriosRule.verificar(to);

		entity = toBuilder.build(to);

		Movimentacoes movimentacoes = repository.save(entity);
		
		alterarListaItensMovimentacoesCmd.alterarAll(to.getItensMovimentacoes(), movimentacoes);
	
		alterarListaPagamentosFaturaCmd.alterarAll(to.getPagamentosFatura(), movimentacoes);
		
		alterarListaFaturasCmd.alterarAll(to.getFaturas(), movimentacoes);

	}

}
