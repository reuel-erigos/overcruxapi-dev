package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.TributoMovimentacaoTOBuilder;
import br.com.crux.dao.repository.TributoMovimentacaoRepository;
import br.com.crux.entity.Movimentacoes;
import br.com.crux.entity.TributosMovimentacoes;
import br.com.crux.to.TributosMovimentacoesTO;

@Component
public class CadastrarTributosMovimentacaoCmd {

	@Autowired TributoMovimentacaoRepository repository;
	@Autowired TributoMovimentacaoTOBuilder tOBuilder;

	public TributosMovimentacoes cadastrar(TributosMovimentacoesTO tributosTO, Long idMovimentacao) {
		tributosTO.setIdMovimentacao(idMovimentacao);
		TributosMovimentacoes entity = tOBuilder.build(tributosTO);
		return repository.save(entity);
	}

	public List<TributosMovimentacoes> cadastrarLista(Movimentacoes movimentacoes, List<TributosMovimentacoesTO> tributosTO) {
		if(Objects.nonNull(tributosTO)) {
			return tributosTO.stream()
					.map(item -> cadastrar(item, movimentacoes.getId() ))
					.collect(Collectors.toList());
		}
		return new ArrayList<TributosMovimentacoes>();
	}

}
