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

	@Autowired PagamentosFaturaRepository repository;
	@Autowired PagamentosFaturaTOBuilder tOBuilder;

	public PagamentosFatura cadastrar(PagamentosFaturaTO pagamentosFaturaTO, Movimentacoes movimentacoes) {
		PagamentosFatura entity = tOBuilder.build(movimentacoes, pagamentosFaturaTO);
		return repository.save(entity);
	}

	public List<PagamentosFatura> cadastrarLista(Movimentacoes movimentacoes, List<PagamentosFaturaTO> list) {
		return list.stream()
				.map(item -> cadastrar(item, movimentacoes))
				.collect(Collectors.toList());

	}

}
