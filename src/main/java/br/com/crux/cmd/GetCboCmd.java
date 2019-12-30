package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.CboTOBuilder;
import br.com.crux.dao.repository.CboRepository;
import br.com.crux.entity.Cbo;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.CboTO;

@Component
public class GetCboCmd {

	@Autowired private CboRepository repository;
	@Autowired private CboTOBuilder tOBuilder;
	
	public List<CboTO> getAll() {
		Optional<List<Cbo>> entitys = Optional.ofNullable(repository.findAll());
		
		if(entitys.isPresent()) {
			return tOBuilder.buildAll(entitys.get());
		}
		return new ArrayList<CboTO>();
	}
	
	public CboTO getById(Long id) {
		Optional<Cbo> entityOptional = repository.findById(id);
		if(!entityOptional.isPresent()) {
			throw new NotFoundException("Cbo não encontrado");
		}
		return tOBuilder.buildTO(entityOptional.get());
	}
			
}
