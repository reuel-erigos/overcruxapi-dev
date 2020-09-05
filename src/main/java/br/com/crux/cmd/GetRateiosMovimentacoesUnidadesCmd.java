package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.RateiosMovimentacoesUnidadesTOBuilder;
import br.com.crux.dao.repository.RateiosMovimentacoesUnidadesRepository;
import br.com.crux.entity.Movimentacoes;
import br.com.crux.entity.RateiosMovimentacoesUnidades;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.RateiosMovimentacoesUnidadesTO;

@Component
public class GetRateiosMovimentacoesUnidadesCmd {

	@Autowired private RateiosMovimentacoesUnidadesTOBuilder toBuilder;
	@Autowired private RateiosMovimentacoesUnidadesRepository repository;

	public RateiosMovimentacoesUnidadesTO getTOById(Long id) {
		Optional<RateiosMovimentacoesUnidades> entityOptional = repository.findById(id);
		if (!entityOptional.isPresent()) {
			throw new NotFoundException("Rateios unidades não encontrado.");
		}
		return toBuilder.buildTO(entityOptional.get());
	}

	public List<RateiosMovimentacoesUnidades> getPorMovimentacoes(Movimentacoes p) {
		return repository.findByIdMovimento(p.getId()).orElse(new ArrayList<RateiosMovimentacoesUnidades>());
	}
	
	public RateiosMovimentacoesUnidades getById(Long id) {
		return repository.findById(id).orElse(null);
	}

}
