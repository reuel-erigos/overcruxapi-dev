package br.com.crux.cmd;

import java.util.Objects;
import java.util.Optional;

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
	@Autowired private AlterarListaRateiosMovimentacoesUnidadesCmd alterarListaRateiosMovimentacoesUnidadesCmd;
	@Autowired private ValidarContaReembolsoRule validarContaReembolsoRule;
	
	public MovimentacoesTO alterar(MovimentacoesTO to) {
		Movimentacoes entity = repository.findById(to.getId()).orElseThrow(() -> new NotFoundException("Movimento informado não existe."));

		camposObrigatoriosRule.verificar(to);
		entity = toBuilder.build(to);
		Movimentacoes movimentacoes = repository.save(entity);

		if(!to.getStTipoMovimentacao().toUpperCase().equals("T")) {
			
			/*
			if(Objects.nonNull(to.getContaBancaria())) {
				validarContaReembolsoRule.verificar(to.getContaBancaria().getId(), to.getPagamentosFatura());
			}
			*/
			
			alterarListaItensMovimentacoesCmd.alterarAll(to.getItensMovimentacoes(), movimentacoes);
			alterarListaRateiosMovimentacoesCmd.alterarAll(to.getRateios(), movimentacoes);
			alterarListaRateiosMovimentacoesUnidadesCmd.alterarAll(to.getRateiosUnidades(), movimentacoes);
			alterarListaPagamentosFaturaCmd.alterarAll(to.getPagamentosFatura(), movimentacoes);
			alterarListaFaturasCmd.alterarAll(to.getFaturas(), movimentacoes);
		}

		Optional<Movimentacoes> entitySalva = repository.findById(movimentacoes.getId());
		return toBuilder.buildTO(entitySalva.get());
	}

}
