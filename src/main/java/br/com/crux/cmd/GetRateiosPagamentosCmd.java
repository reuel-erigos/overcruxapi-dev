package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.RateiosPagamentosTOBuilder;
import br.com.crux.dao.repository.RateiosPagamentosRepository;
import br.com.crux.entity.PagamentosFatura;
import br.com.crux.entity.RateiosPagamentos;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.RateiosPagamentosTO;

@Component
public class GetRateiosPagamentosCmd {

	@Autowired private RateiosPagamentosTOBuilder toBuilder;
	@Autowired private RateiosPagamentosRepository repository;

	public RateiosPagamentos getById(Long id) {
		return repository.findById(id).orElse(null);

	}

	public RateiosPagamentosTO getTOById(Long id) {
		Optional<RateiosPagamentos> entityOptional = repository.findById(id);
		if (!entityOptional.isPresent()) {
			throw new NotFoundException("Rateio de pagamento não encontrado.");
		}
		return toBuilder.buildTO(entityOptional.get());
	}

	public List<RateiosPagamentosTO> getTOByIdPagamentoFatura(Long idPagamento) {
		Optional<List<RateiosPagamentos>> lista = repository.findByIdPagamentoFatura(idPagamento);

		if (lista.isPresent()) {
			return toBuilder.buildAll(lista.get());
		}

		return Collections.emptyList();

	}

	public List<RateiosPagamentos> getPorPagamentoFatura(PagamentosFatura p) {
		return repository.findByIdPagamentoFatura(p.getId())
				.orElse(new ArrayList<RateiosPagamentos>());
	}

}
