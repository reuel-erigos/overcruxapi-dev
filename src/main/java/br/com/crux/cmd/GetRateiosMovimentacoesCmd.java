package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.RateiosMovimentacoesTOBuilder;
import br.com.crux.dao.repository.RateiosMovimentacoesRepository;
import br.com.crux.entity.Movimentacoes;
import br.com.crux.entity.RateiosMovimentacoes;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.RateiosMovimentacoesTO;

@Component
public class GetRateiosMovimentacoesCmd {

	@Autowired private RateiosMovimentacoesTOBuilder toBuilder;
	@Autowired private RateiosMovimentacoesRepository repository;

	public RateiosMovimentacoesTO getTOById(Long id) {
		Optional<RateiosMovimentacoes> entityOptional = repository.findById(id);
		if (!entityOptional.isPresent()) {
			throw new NotFoundException("Rateios movimentação não encontrado.");
		}
		return toBuilder.buildTO(entityOptional.get());
	}

	public List<RateiosMovimentacoes> getPorMovimentacoes(Movimentacoes p) {
		return repository.findByIdMovimento(p.getId()).orElse(new ArrayList<RateiosMovimentacoes>());
	}
	
	public RateiosMovimentacoes getById(Long id) {
		return repository.findById(id).orElse(null);
	}

}
