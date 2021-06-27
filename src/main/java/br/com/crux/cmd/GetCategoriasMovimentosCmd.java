package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.CategoriasMovimentosTOBuilder;
import br.com.crux.dao.repository.CategoriasMovimentosRepository;
import br.com.crux.entity.CategoriasMovimentos;
import br.com.crux.entity.Movimentacoes;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.CategoriasMovimentosTO;

@Component
public class GetCategoriasMovimentosCmd {

	@Autowired private CategoriasMovimentosRepository repository;
	@Autowired private CategoriasMovimentosTOBuilder toBuilder;

	public CategoriasMovimentos getById(Long id) {
		return repository.findById(id).orElse(null);
	}

	public CategoriasMovimentosTO getTOById(Long id) {
		Optional<CategoriasMovimentos> entityOptional = repository.findById(id);
		if (!entityOptional.isPresent()) {
			throw new NotFoundException("Categoria movimento não encontrado.");
		}
		return toBuilder.buildTO(entityOptional.get());
	}

	public List<CategoriasMovimentosTO> getCategoriasMovimentosTOByMovimentacao(Movimentacoes movimentacoes) {
		Optional<List<CategoriasMovimentos>> lista = repository.findByIdMovimentacao(movimentacoes.getId());

		if (lista.isPresent()) {
			return toBuilder.buildAll(lista.get());
		}

		return Collections.emptyList();

	}

	public List<CategoriasMovimentos> getPorMovimentacoes(Movimentacoes p) {
		return repository.findByIdMovimentacao(p.getId())
				.orElse(new ArrayList<CategoriasMovimentos>());
	}


}
