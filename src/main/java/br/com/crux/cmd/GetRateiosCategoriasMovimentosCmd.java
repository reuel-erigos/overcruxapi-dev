package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.RateiosCategoriasMovimentosTOBuilder;
import br.com.crux.dao.repository.RateiosCategoriasMovimentosRepository;
import br.com.crux.entity.CategoriasMovimentos;
import br.com.crux.entity.RateiosCategoriasMovimentos;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.RateiosCategoriasMovimentosTO;

@Component
public class GetRateiosCategoriasMovimentosCmd {

	@Autowired private RateiosCategoriasMovimentosTOBuilder toBuilder;
	@Autowired private RateiosCategoriasMovimentosRepository repository;

	public RateiosCategoriasMovimentos getById(Long id) {
		return repository.findById(id).orElse(null);
	}

	public RateiosCategoriasMovimentosTO getTOById(Long id) {
		Optional<RateiosCategoriasMovimentos> entityOptional = repository.findById(id);
		if (!entityOptional.isPresent()) {
			throw new NotFoundException("Rateio de categoria movimento não encontrado.");
		}
		return toBuilder.buildTO(entityOptional.get());
	}

	public List<RateiosCategoriasMovimentosTO> getTOByIdCategoria(Long idCategoria) {
		Optional<List<RateiosCategoriasMovimentos>> lista = repository.findByIdCategoria(idCategoria);

		if (lista.isPresent()) {
			return toBuilder.buildAllTO(lista.get());
		}

		return Collections.emptyList();

	}

	public List<RateiosCategoriasMovimentos> getPorCategoriaMovimento(CategoriasMovimentos p) {
		return repository.findByIdCategoria(p.getId()).orElse(new ArrayList<RateiosCategoriasMovimentos>());
	}

}
