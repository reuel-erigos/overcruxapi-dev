package br.com.crux.cmd;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ItensMovimentacoesTOBuilder;
import br.com.crux.dao.repository.ItensMovimentacoesRepository;
import br.com.crux.entity.ItensMovimentacoes;
import br.com.crux.entity.Movimentacoes;
import br.com.crux.to.ItensMovimentacoesTO;

@Component
public class CadastrarItensMovimentacoesCmd {

	@Autowired ItensMovimentacoesRepository repository;
	@Autowired ItensMovimentacoesTOBuilder tOBuilder;

	public ItensMovimentacoes cadastrar(ItensMovimentacoesTO itensMovimentacoesTO, Movimentacoes movimentacoes) {
		ItensMovimentacoes entity = tOBuilder.build(movimentacoes, itensMovimentacoesTO);
		return repository.save(entity);
	}

	public List<ItensMovimentacoes> cadastrarLista(Movimentacoes movimentacoes, List<ItensMovimentacoesTO> list) {
		return list.stream()
				.map(item -> cadastrar(item,movimentacoes))
				.collect(Collectors.toList());

	}

}
