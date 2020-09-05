package br.com.crux.cmd;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.RateiosMovimentacoesUnidadesTOBuilder;
import br.com.crux.dao.repository.RateiosMovimentacoesUnidadesRepository;
import br.com.crux.entity.Movimentacoes;
import br.com.crux.entity.RateiosMovimentacoesUnidades;
import br.com.crux.to.RateiosMovimentacoesUnidadesTO;

@Component
public class CadastrarRateiosMovimentacoesUnidadesCmd {

	@Autowired RateiosMovimentacoesUnidadesRepository repository;
	@Autowired RateiosMovimentacoesUnidadesTOBuilder tOBuilder;

	public RateiosMovimentacoesUnidades cadastrar(Long idMovimentacao, RateiosMovimentacoesUnidadesTO rateioMovimentacoesTO) {
		rateioMovimentacoesTO.setIdMovimentacao(idMovimentacao);
		RateiosMovimentacoesUnidades entity = tOBuilder.build(rateioMovimentacoesTO);
		return repository.save(entity);
	}

	public List<RateiosMovimentacoesUnidades> cadastrarLista(Movimentacoes movimentacoes, List<RateiosMovimentacoesUnidadesTO> rateios) {
		return rateios.stream()
						.map(item -> cadastrar(movimentacoes.getId(), item))
						.collect(Collectors.toList());
	}

}
