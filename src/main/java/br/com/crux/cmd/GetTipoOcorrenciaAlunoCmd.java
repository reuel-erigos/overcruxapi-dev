package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.TipoOcorrenciaAlunoTOBuilder;
import br.com.crux.dao.repository.TipoOcorrenciaAlunoRepository;
import br.com.crux.entity.TipoOcorrenciaAluno;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.TipoOcorrenciaAlunoTO;

@Component
public class GetTipoOcorrenciaAlunoCmd {

	@Autowired private TipoOcorrenciaAlunoRepository repository;
	@Autowired private TipoOcorrenciaAlunoTOBuilder tOBuilder;
	
	public List<TipoOcorrenciaAlunoTO> getAll() {
		Optional<List<TipoOcorrenciaAluno>> entitys = Optional.ofNullable(repository.findAll());
		
		if(entitys.isPresent()) {
			return tOBuilder.buildAll(entitys.get());
		}
		return new ArrayList<TipoOcorrenciaAlunoTO>();
	}
	
	public TipoOcorrenciaAlunoTO getById(Long id) {
		Optional<TipoOcorrenciaAluno> entityOptional = repository.findById(id);
		if(!entityOptional.isPresent()) {
			throw new NotFoundException("Tipo de ocorrência não encontrado.");
		}
		return tOBuilder.buildTO(entityOptional.get());
	}
			
}
