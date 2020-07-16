package br.com.crux.cmd;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.RateiosMovimentacoesTOBuilder;
import br.com.crux.dao.repository.RateiosMovimentacoesRepository;
import br.com.crux.entity.Movimentacoes;
import br.com.crux.entity.RateiosMovimentacoes;
import br.com.crux.to.RateiosMovimentacoesTO;

@Component
public class CadastrarRateiosMovimentacoesCmd {

	@Autowired RateiosMovimentacoesRepository repository;
	@Autowired RateiosMovimentacoesTOBuilder tOBuilder;

	public RateiosMovimentacoes cadastrar(Long idMovimentacao, RateiosMovimentacoesTO rateioMovimentacoesTO) {
		rateioMovimentacoesTO.setIdMovimentacao(idMovimentacao);
		RateiosMovimentacoes entity = tOBuilder.build(rateioMovimentacoesTO);
		return repository.save(entity);
	}

	public List<RateiosMovimentacoes> cadastrarLista(Movimentacoes movimentacoes, List<RateiosMovimentacoesTO> rateios) {
		return rateios.stream()
						.map(item -> cadastrar(movimentacoes.getId(), item))
						.collect(Collectors.toList());
	}

}
