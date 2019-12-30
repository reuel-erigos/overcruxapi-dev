package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.AvaliacoesTOBuilder;
import br.com.crux.dao.repository.AvaliacoesRepository;
import br.com.crux.entity.Avaliacoes;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.AvaliacoesTO;

@Component
public class GetAvaliacoesCmd {

	@Autowired private AvaliacoesRepository repository;
	@Autowired private AvaliacoesTOBuilder toBuilder;

	public List<AvaliacoesTO> getAll() {
		List<Avaliacoes> entitys = repository.findAll();
		if (entitys == null || entitys.isEmpty()) {
			return new ArrayList<AvaliacoesTO>();
		}
		return toBuilder.buildAll(entitys);
	}

	public AvaliacoesTO getTOById(Long id) {
		Avaliacoes entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Avaliação não encontrada."));
		return toBuilder.buildTO(entity);
	}

	public Avaliacoes getById(Long id) {
		return repository.findById(id).orElseGet(null);
	}

}
