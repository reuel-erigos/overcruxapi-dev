package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.PagamentosFaturaTOBuilder;
import br.com.crux.dao.repository.PagamentosFaturaRepository;
import br.com.crux.entity.Movimentacoes;
import br.com.crux.entity.PagamentosFatura;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.PagamentosFaturaTO;

@Component
public class GetPagamentosFaturaCmd {

	@Autowired private PagamentosFaturaTOBuilder toBuilder;
	@Autowired private PagamentosFaturaRepository repository;

	public PagamentosFatura getById(Long id) {
		return repository.findById(id)
				.orElse(null);

	}

	public PagamentosFaturaTO getTOById(Long id) {
		Optional<PagamentosFatura> entityOptional = repository.findById(id);
		if (!entityOptional.isPresent()) {
			throw new NotFoundException("Pagamento da fatura não encontrado.");
		}
		return toBuilder.buildTO(entityOptional.get());
	}

	public List<PagamentosFaturaTO> getPagamentoFaturaTOByMovimentacao(Movimentacoes movimentacoes) {
		Optional<List<PagamentosFatura>> lista = repository.findByIdMovimentacao(movimentacoes.getId());

		if (lista.isPresent()) {
			return toBuilder.buildAll(lista.get());
		}

		return Collections.emptyList();

	}

	public List<PagamentosFatura> getPorMovimentacoes(Movimentacoes p) {
		return repository.findByIdMovimentacao(p.getId())
				.orElse(new ArrayList<PagamentosFatura>());
	}

}
