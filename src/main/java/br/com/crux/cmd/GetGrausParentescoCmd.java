package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import br.com.crux.builder.GrausParentescoTOBuilder;
import br.com.crux.dao.repository.GrausParentescoRepository;
import br.com.crux.entity.GrausParentesco;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.GrausParentescoTO;

@Component
public class GetGrausParentescoCmd {

	@Autowired private GrausParentescoRepository repository;
	@Autowired private GrausParentescoTOBuilder toBuilder;
	
	
	
	public List<GrausParentescoTO> getAll() {
		Sort sort = Sort.by(Direction.ASC, "nome");
		List<GrausParentescoTO> entitys = toBuilder.buildAll(repository.findAll(sort));
		if(entitys == null || entitys.isEmpty()) {
			return new ArrayList<GrausParentescoTO>();
		}
		return entitys;
	}
	
	public GrausParentescoTO getTOById(Long id) {
		Optional<GrausParentesco> entityOptional = repository.findById(id);
		if(!entityOptional.isPresent()) {
			throw new NotFoundException("Graus de Instrução não encontrado");
		}
		return toBuilder.buildTO(entityOptional.get());
	}

	public GrausParentesco getById(Long id) {
		return repository.findById(id).orElse(null);
	}
			
}
