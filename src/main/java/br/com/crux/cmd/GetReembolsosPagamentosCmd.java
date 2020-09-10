package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ReembolsosPagamentosTOBuilder;
import br.com.crux.dao.repository.ReembolsosPagamentosRepository;
import br.com.crux.entity.PagamentosFatura;
import br.com.crux.entity.ReembolsosPagamentos;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.ReembolsosPagamentosTO;

@Component
public class GetReembolsosPagamentosCmd {

	@Autowired private ReembolsosPagamentosTOBuilder toBuilder;
	@Autowired private ReembolsosPagamentosRepository repository;

	public ReembolsosPagamentos getById(Long id) {
		return repository.findById(id).orElse(null);

	}

	public ReembolsosPagamentosTO getTOById(Long id) {
		Optional<ReembolsosPagamentos> entityOptional = repository.findById(id);
		if (!entityOptional.isPresent()) {
			throw new NotFoundException("Reembolso pagamento não encontrado.");
		}
		return toBuilder.buildTO(entityOptional.get());
	}

	public List<ReembolsosPagamentosTO> getReembolsoPagamentoTOByIdPagamentoFatura(Long idPagamento) {
		Optional<List<ReembolsosPagamentos>> lista = repository.findByIdPagamentoFatura(idPagamento);

		if (lista.isPresent()) {
			return toBuilder.buildAll(lista.get());
		}

		return Collections.emptyList();

	}

	public List<ReembolsosPagamentos> getPorPagamentoFatura(PagamentosFatura p) {
		return repository.findByIdPagamentoFatura(p.getId())
				.orElse(new ArrayList<ReembolsosPagamentos>());
	}

}
