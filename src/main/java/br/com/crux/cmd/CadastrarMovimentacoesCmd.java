package br.com.crux.cmd;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.MovimentacoesTOBuilder;
import br.com.crux.dao.repository.MovimentacoesRepository;
import br.com.crux.entity.Movimentacoes;
import br.com.crux.rule.CamposObrigatoriosMovimentacoesRule;
import br.com.crux.to.MovimentacoesTO;

@Component
public class CadastrarMovimentacoesCmd {

	@Autowired private MovimentacoesRepository repository;
	@Autowired private MovimentacoesTOBuilder toBuilder;
	@Autowired private CamposObrigatoriosMovimentacoesRule camposObrigatoriosRule;
	@Autowired private CadastrarItensMovimentacoesCmd cadastrarItensMovimentacoesCmd;
	@Autowired private CadastrarFaturasCmd cadastrarFaturasCmd;
	@Autowired private CadastrarPagamentosFaturaCmd cadastrarPagamentosFaturaCmd;
	@Autowired private CadastrarRateiosMovimentacoesCmd cadastrarRateiosMovimentacoesCmd;
	@Autowired private CadastrarRateiosMovimentacoesUnidadesCmd cadastrarRateiosMovimentacoesUnidadesCmd;
	@Autowired private CadastrarTributosMovimentacaoCmd cadastrarTributosMovimentacaoCmd;

	public MovimentacoesTO cadastrar(MovimentacoesTO to) {
		camposObrigatoriosRule.verificar(to);
		to.setDataMovimentacao(LocalDateTime.now());
		Movimentacoes entity = toBuilder.build(to);

		Movimentacoes movimentacoes = repository.save(entity);
		
		if(!to.getStTipoMovimentacao().toUpperCase().equals("T")) {
			cadastrarRateiosMovimentacoesCmd.cadastrarLista(movimentacoes, to.getRateios());
			cadastrarRateiosMovimentacoesUnidadesCmd.cadastrarLista(movimentacoes, to.getRateiosUnidades());
			cadastrarItensMovimentacoesCmd.cadastrarLista(movimentacoes, to.getItensMovimentacoes());
			cadastrarFaturasCmd.cadastrarLista(movimentacoes, to.getFaturas());
			cadastrarPagamentosFaturaCmd.cadastrarLista(movimentacoes, to.getPagamentosFatura());
			cadastrarTributosMovimentacaoCmd.cadastrarLista(movimentacoes, to.getTributos());
		}

		Optional<Movimentacoes> entitySalva = repository.findById(movimentacoes.getId());
		return toBuilder.buildTO(entitySalva.get());
	}
}
