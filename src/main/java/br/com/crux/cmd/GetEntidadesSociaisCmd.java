package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.EntidadesSociaisTOBuilder;
import br.com.crux.dao.repository.EntidadesSociaisRepository;
import br.com.crux.entity.EntidadesSociais;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.EntidadesSociaisTO;

@Component
public class GetEntidadesSociaisCmd {

	@Autowired private EntidadesSociaisRepository repository;
	@Autowired private EntidadesSociaisTOBuilder toBuilder;
	
	
	public List<EntidadesSociaisTO> getAll() {
		List<EntidadesSociaisTO> entitys = toBuilder.buildAll(repository.findAll());
		if(entitys == null || entitys.isEmpty()) {
			return new ArrayList<EntidadesSociaisTO>();
		}
		return entitys;
	}
	
	public EntidadesSociaisTO getById(Long id) {
		Optional<EntidadesSociais> entityOptional = repository.findById(id);
		if(!entityOptional.isPresent()) {
			throw new NotFoundException("Acao não encontrada.");
		}
		return toBuilder.buildTO(entityOptional.get());
	}
			
}
