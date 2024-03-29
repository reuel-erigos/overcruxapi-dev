package br.com.crux.cmd;

import java.util.Optional;

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
	@Autowired private AlterarListaRateiosMovimentacoesCmd alterarListaRateiosMovimentacoesCmd;
	@Autowired private AlterarListaRateiosMovimentacoesUnidadesCmd alterarListaRateiosMovimentacoesUnidadesCmd;
	@Autowired private AlterarListaTributosMovimentacaoCmd alterarListaTributosMovimentacaoCmd;
	@Autowired private AlterarListaCategoriasMovimentosCmd alterarListaCategoriasMovimentosCmd;
	
	public MovimentacoesTO alterar(MovimentacoesTO to) {
		Movimentacoes entity = repository.findById(to.getId()).orElseThrow(() -> new NotFoundException("Movimento informado não existe."));

		camposObrigatoriosRule.verificar(to);
		entity = toBuilder.build(to);
		Movimentacoes movimentacoes = repository.save(entity);

		if(!to.getStTipoMovimentacao().toUpperCase().equals("T")) {
			alterarListaTributosMovimentacaoCmd.alterarAll(to.getTributos(), movimentacoes);
			alterarListaItensMovimentacoesCmd.alterarAll(to.getItensMovimentacoes(), movimentacoes);
			alterarListaRateiosMovimentacoesCmd.alterarAll(to.getRateios(), movimentacoes);
			alterarListaRateiosMovimentacoesUnidadesCmd.alterarAll(to.getRateiosUnidades(), movimentacoes);
			alterarListaFaturasCmd.alterarAll(to.getFaturas(), movimentacoes);
			alterarListaPagamentosFaturaCmd.alterarAll(to.getPagamentosFatura(), movimentacoes);
			alterarListaCategoriasMovimentosCmd.alterarAll(to.getCategoriasMovimentos(), movimentacoes);
		}
		

		Optional<Movimentacoes> entitySalva = repository.findById(movimentacoes.getId());
		return toBuilder.buildTO(entitySalva.get());
	}

}
