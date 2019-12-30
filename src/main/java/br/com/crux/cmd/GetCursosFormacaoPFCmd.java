package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.CursosFormacaoPfTOBuilder;
import br.com.crux.dao.repository.CursosFormacaoPFRepository;
import br.com.crux.entity.CursosFormacaoPf;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.CursosFormacaoPfTO;

@Component
public class GetCursosFormacaoPFCmd {

	@Autowired private CursosFormacaoPFRepository repository;
	@Autowired private CursosFormacaoPfTOBuilder toBuilder;
	
	
	
	public List<CursosFormacaoPfTO> getAll() {
		List<CursosFormacaoPfTO> entitys = toBuilder.buildAll(repository.findAll());
		if(entitys == null || entitys.isEmpty()) {
			return new ArrayList<CursosFormacaoPfTO>();
		}
		return entitys;
	}
	
	public CursosFormacaoPfTO getById(Long id) {
		Optional<CursosFormacaoPf> entityOptional = repository.findById(id);
		if(!entityOptional.isPresent()) {
			throw new NotFoundException("Curso Formação não encontrado");
		}
		return toBuilder.buildTO(entityOptional.get());
	}
			
}
