package br.com.crux.cmd;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.FaturaTOBuilder;
import br.com.crux.dao.repository.FaturaRepository;
import br.com.crux.entity.Fatura;
import br.com.crux.entity.Movimentacoes;
import br.com.crux.to.FaturaTO;

@Component
public class CadastrarFaturasCmd {

	@Autowired FaturaRepository repository;
	@Autowired FaturaTOBuilder tOBuilder;

	public Fatura cadastrar(FaturaTO faturaTO, Movimentacoes movimentacoes) {
		Fatura entity = tOBuilder.build(movimentacoes, faturaTO);
		return repository.save(entity);
	}

	public List<Fatura> cadastrarLista(Movimentacoes movimentacoes, List<FaturaTO> list) {
		return list.stream()
				.map(item -> cadastrar(item, movimentacoes))
				.collect(Collectors.toList());

	}

}
