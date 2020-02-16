package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.FaturaTOBuilder;
import br.com.crux.dao.repository.FaturaRepository;
import br.com.crux.entity.Fatura;
import br.com.crux.entity.Movimentacoes;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.FaturaTO;

@Component
public class GetFaturaCmd {

	@Autowired private FaturaTOBuilder toBuilder;
	@Autowired private FaturaRepository repository;

	public FaturaTO getById(Long id) {
		Optional<Fatura> entityOptional = repository.findById(id);
		if (!entityOptional.isPresent()) {
			throw new NotFoundException("Fatura não encontrada.");
		}
		return toBuilder.buildTO(entityOptional.get());
	}

	public List<FaturaTO> getFaturaTOByMovimentacao(Movimentacoes movimentacoes) {
		Optional<List<Fatura>> lista = repository.findByMovimentacao(movimentacoes);

		if (lista.isPresent()) {
			return toBuilder.buildAll(lista.get());
		}

		return Collections.emptyList();

	}

	public List<Fatura> getPorMovimentacoes(Movimentacoes p) {
		return repository.findByMovimentacao(p)
				.orElse(new ArrayList<Fatura>());
	}

}
