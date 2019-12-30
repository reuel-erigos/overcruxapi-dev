package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.TiposContratacoesTOBuilder;
import br.com.crux.dao.repository.TiposContratacoesRepository;
import br.com.crux.entity.TiposContratacoes;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.TiposContratacoesTO;

@Component
public class GetTiposContratacoesCmd {

	@Autowired private TiposContratacoesRepository repository;
	@Autowired private TiposContratacoesTOBuilder toBuilder;

	public List<TiposContratacoesTO> getAll() {
		List<TiposContratacoes> listaRetorno = repository.findAll();

		if (Objects.nonNull(listaRetorno)) {
			return toBuilder.buildAll(listaRetorno);
		}
		return new ArrayList<TiposContratacoesTO>();
	}

	public TiposContratacoesTO getTOById(Long id) {
		TiposContratacoes entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Tipo contratação não encontrado."));
		return toBuilder.buildTO(entity);
	}

	public TiposContratacoes getById(Long id) {
		return repository.findById(id).orElseGet(null);
	}

}
