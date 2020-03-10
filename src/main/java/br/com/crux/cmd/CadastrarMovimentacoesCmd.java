package br.com.crux.cmd;

import java.time.LocalDateTime;

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

	public void cadastrar(MovimentacoesTO to) {

		camposObrigatoriosRule.verificar(to);

		to.setDataMovimentacao(LocalDateTime.now());
		Movimentacoes entity = toBuilder.build(to);

		Movimentacoes movimentacoes = repository.save(entity);
		
		cadastrarItensMovimentacoesCmd.cadastrarLista(movimentacoes, to.getItensMovimentacoes());
		
		cadastrarFaturasCmd.cadastrarLista(movimentacoes, to.getFaturas());
		
		cadastrarPagamentosFaturaCmd.cadastrarLista(movimentacoes, to.getPagamentosFatura());

	}
}
