package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ColaboradoresProgramaTOBuilder;
import br.com.crux.dao.repository.ColaboradoresProgramaRepository;
import br.com.crux.entity.ColaboradoresPrograma;
import br.com.crux.entity.Programa;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.ColaboradoresProgramaTO;

@Component
public class GetColaboradoresProgramaCmd {

	@Autowired private ColaboradoresProgramaRepository repository;
	@Autowired private ColaboradoresProgramaTOBuilder toBuilder;

	public ColaboradoresProgramaTO getById(Long id) {
		Optional<ColaboradoresPrograma> entityOptional = repository.findById(id);
		if (!entityOptional.isPresent()) {
			throw new NotFoundException("Colaborador do Programa não encontrado.");
		}
		return toBuilder.buildTO(entityOptional.get());
	}

	public List<ColaboradoresProgramaTO> getColaboradoresProgramaTOByPrograma(Programa programa) {
		Optional<List<ColaboradoresPrograma>> lista = repository.findByPrograma(programa);

		if (lista.isPresent()) {
			return toBuilder.buildAllParaLista(lista.get());
		}

		return Collections.emptyList();

	}

	
	public List<ColaboradoresPrograma> getPorPrograma(Programa programa) {
		return repository.findByPrograma(programa).orElse(new ArrayList<ColaboradoresPrograma>());
	}
}
