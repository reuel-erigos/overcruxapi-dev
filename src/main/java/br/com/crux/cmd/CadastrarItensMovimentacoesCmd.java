package br.com.crux.cmd;

import java.util.List;
import java.util.Objects;
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

	@Autowired private ItensMovimentacoesRepository repository;
	@Autowired private ItensMovimentacoesTOBuilder tOBuilder;
	@Autowired private AlterarListaTributosItensMovimentacaoCmd alterarListaTributosItensMovimentacaoCmd;

	public ItensMovimentacoes cadastrar(ItensMovimentacoesTO itemTO, Movimentacoes movimentacao) {
		ItensMovimentacoes entity = tOBuilder.build(movimentacao, itemTO);
		entity = repository.save(entity);
		alterarListaTributosItensMovimentacaoCmd.alterarAll(itemTO.getTributos(), entity);
		return entity;
	}

	public List<ItensMovimentacoes> cadastrarLista(Movimentacoes movimentacao, List<ItensMovimentacoesTO> list) {
		return list.stream()
				.map(item -> cadastrar(item,movimentacao))
				.collect(Collectors.toList());

	}

}
