package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.CondicoesMoradiaTOBuilder;
import br.com.crux.dao.repository.CondicoesMoradiaRepository;
import br.com.crux.entity.CondicoesMoradia;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.CondicoesMoradiaTO;

@Component
public class GetCondicoesMoradiaCmd {

	@Autowired private CondicoesMoradiaRepository repository;
	@Autowired private CondicoesMoradiaTOBuilder toBuilder;
	
	
	public List<CondicoesMoradiaTO> getAll() {
		List<CondicoesMoradia> entitys = repository.findAll();
		if(entitys == null || entitys.isEmpty()) {
			return new ArrayList<CondicoesMoradiaTO>();
		}
		return toBuilder.buildAll(entitys);
	}
	
	public CondicoesMoradiaTO getTOById(Long id) {
		Optional<CondicoesMoradia> entityOptional = repository.findById(id);
		if(!entityOptional.isPresent()) {
			throw new NotFoundException("Condição de moradia não encontrada.");
		}
		return toBuilder.buildTO(entityOptional.get());
	}

	public CondicoesMoradia getById(Long id) {
		return repository.findById(id).orElse(null);
		
	}
			
}
