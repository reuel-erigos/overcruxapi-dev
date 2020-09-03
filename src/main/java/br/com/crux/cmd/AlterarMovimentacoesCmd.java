package br.com.crux.cmd;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.MovimentacoesTOBuilder;
import br.com.crux.dao.repository.MovimentacoesRepository;
import br.com.crux.entity.Movimentacoes;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosMovimentacoesRule;
import br.com.crux.rule.ValidarContaReembolsoRule;
import br.com.crux.to.MovimentacoesTO;

@Component
public class AlterarMovimentacoesCmd {

	@Autowired private MovimentacoesRepository repository;
	@Autowired private MovimentacoesTOBuilder toBuilder;
	@Autowired private CamposObrigatoriosMovimentacoesRule camposObrigatoriosRule;
	@Autowired private AlterarListaItensMovimentacoesCmd alterarListaItensMovimentacoesCmd;
	@Autowired private AlterarListaFaturasCmd alterarListaFaturasCmd;
	@Autowired private AlterarListaPagamentosFaturaCmd alterarListaPagamentosFaturaCmd;
	@Autowired private AlterarListaRateiosMovimentacoesCmd alterarListaRateiosMovimentacoesCmd;
	@Autowired private ValidarContaReembolsoRule validarContaReembolsoRule;
	
	public void alterar(MovimentacoesTO to) {
		Movimentacoes entity = repository.findById(to.getId()).orElseThrow(() -> new NotFoundException("Movimento informado não existe."));

		camposObrigatoriosRule.verificar(to);
		
		if(Objects.nonNull(to.getContaBancaria())) {
			validarContaReembolsoRule.verificar(to.getContaBancaria().getId(), to.getPagamentosFatura());
		}

		entity = toBuilder.build(to);

		Movimentacoes movimentacoes = repository.save(entity);
		
		alterarListaItensMovimentacoesCmd.alterarAll(to.getItensMovimentacoes(), movimentacoes);
		
		alterarListaRateiosMovimentacoesCmd.alterarAll(to.getRateios(), movimentacoes);
	
		alterarListaPagamentosFaturaCmd.alterarAll(to.getPagamentosFatura(), movimentacoes);
		
		alterarListaFaturasCmd.alterarAll(to.getFaturas(), movimentacoes);

	}

}
