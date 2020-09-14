package br.com.crux.cmd;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.TributosItensMovimentacaoTOBuilder;
import br.com.crux.dao.repository.TributosItensMovimentacaoRepository;
import br.com.crux.entity.ItensMovimentacoes;
import br.com.crux.entity.TributosItensMovimentacoes;
import br.com.crux.to.TributosItensMovimentacoesTO;

@Component
public class CadastrarTributosItensMovimentacaoCmd {

	@Autowired TributosItensMovimentacaoRepository repository;
	@Autowired TributosItensMovimentacaoTOBuilder tOBuilder;

	public TributosItensMovimentacoes cadastrar(TributosItensMovimentacoesTO tributosTO, Long idItemMovimentacao) {
		tributosTO.setIdItemMovimentacao(idItemMovimentacao);
		TributosItensMovimentacoes entity = tOBuilder.build(tributosTO);
		return repository.save(entity);
	}

	public List<TributosItensMovimentacoes> cadastrarLista(ItensMovimentacoes itemMovimento, List<TributosItensMovimentacoesTO> tributosTO) {
		return tributosTO.stream()
						.map(item -> cadastrar(item, itemMovimento.getId() ))
						.collect(Collectors.toList());
	}

}
