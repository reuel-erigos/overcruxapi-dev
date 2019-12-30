package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ColaboradoresProjetoTOBuilder;
import br.com.crux.dao.repository.ColaboradoresProjetoRepository;
import br.com.crux.entity.ColaboradoresProjeto;
import br.com.crux.entity.Projeto;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.ColaboradoresProjetoTO;

@Component
public class GetColaboradoresProjetoCmd {

	@Autowired private ColaboradoresProjetoRepository repository;
	@Autowired private ColaboradoresProjetoTOBuilder toBuilder;
	@Autowired private ColaboradoresProjetoRepository colaboradoresProjetoRepository;

	public ColaboradoresProjetoTO getById(Long id) {
		Optional<ColaboradoresProjeto> entityOptional = repository.findById(id);
		if (!entityOptional.isPresent()) {
			throw new NotFoundException("Colaborador do Projeto não encontrado.");
		}
		return toBuilder.buildTO(entityOptional.get());
	}

	public List<ColaboradoresProjetoTO> getColaboradoresProjetoTOByProjeto(Projeto projeto) {
		Optional<List<ColaboradoresProjeto>> lista = repository.findByProjeto(projeto);

		if (lista.isPresent()) {
			return toBuilder.buildAllParaLista(lista.get());
		}

		return Collections.emptyList();

	}
	
	public List<ColaboradoresProjeto> getPorProjeto(Projeto projeto){
		return colaboradoresProjetoRepository.findByProjeto(projeto).orElse(new ArrayList<ColaboradoresProjeto>());
	}

}
