package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.SolucoesTOBuilder;
import br.com.crux.dao.repository.SolucoesRepository;
import br.com.crux.entity.Solucoes;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.SolucoesTO;

@Component
public class GetSolucoesCmd {

	@Autowired private SolucoesRepository repository;
	@Autowired private SolucoesTOBuilder toBuilder;

	public List<SolucoesTO> getAll() {
		List<SolucoesTO> entitys = toBuilder.buildAll(repository.findAll());
		if (entitys == null || entitys.isEmpty()) {
			return new ArrayList<SolucoesTO>();
		}
		return entitys;
	}

	public SolucoesTO getTOById(Long id) {
		Solucoes entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Solução não encontrada."));
		return toBuilder.buildTO(entity);
	}

	public Solucoes getById(Long id) {
		return repository.findById(id).orElseGet(null);
	}

}
