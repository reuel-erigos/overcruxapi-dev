package br.com.crux.cmd;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.PagamentosFaturaTOBuilder;
import br.com.crux.dao.repository.PagamentosFaturaRepository;
import br.com.crux.entity.Movimentacoes;
import br.com.crux.entity.PagamentosFatura;
import br.com.crux.to.PagamentosFaturaTO;

@Component
public class CadastrarPagamentosFaturaCmd {

	@Autowired private PagamentosFaturaRepository repository;
	@Autowired private PagamentosFaturaTOBuilder tOBuilder;
	@Autowired private AlterarListaReembolsoPagamentosCmd alterarListaReembolsoPagamentosCmd;
	@Autowired private AlterarListaRateiosPagamentosCmd alterarListaRateiosPagamentosCmd;

	public PagamentosFatura cadastrar(PagamentosFaturaTO pagamentosFaturaTO, Movimentacoes movimentacoes) {
		PagamentosFatura entity = tOBuilder.build(movimentacoes, pagamentosFaturaTO);
		PagamentosFatura entitySalva = repository.save(entity);
		
		alterarListaReembolsoPagamentosCmd.alterarAll(pagamentosFaturaTO.getReembolsos(), entitySalva);
		alterarListaRateiosPagamentosCmd.alterarAll(pagamentosFaturaTO.getRateioPagamento(), entitySalva);
		
		return entitySalva;
	}

	public List<PagamentosFatura> cadastrarLista(Movimentacoes movimentacoes, List<PagamentosFaturaTO> lista) {
		return lista.stream()
				.map(item -> cadastrar(item, movimentacoes))
				.collect(Collectors.toList());

	}

}
