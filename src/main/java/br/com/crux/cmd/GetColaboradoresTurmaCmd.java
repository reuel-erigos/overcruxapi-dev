package br.com.crux.cmd;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ColaboradoresTurmaTOBuilder;
import br.com.crux.dao.repository.ColaboradoresTurmaRepository;
import br.com.crux.entity.ColaboradoresTurma;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.ColaboradoresTurmaTO;

@Component
public class GetColaboradoresTurmaCmd {

	@Autowired private ColaboradoresTurmaRepository repository;
	@Autowired private ColaboradoresTurmaTOBuilder toBuilder;

	public ColaboradoresTurmaTO getById(Long id) {
		Optional<ColaboradoresTurma> entityOptional = repository.findById(id);
		if (!entityOptional.isPresent()) {
			throw new NotFoundException("Colaborador da turma não encontrado.");
		}
		return toBuilder.buildTO(entityOptional.get());
	}

	public List<ColaboradoresTurmaTO> getColaboradoresProjetoTOByTurma(Long idTurma) {
		Optional<List<ColaboradoresTurma>> lista = repository.findByIdTurma(idTurma);

		if (lista.isPresent()) {
			return toBuilder.buildAll(lista.get());
		}

		return Collections.emptyList();

	}
	

}
