package br.com.crux.cmd;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.DependentesTOBuilder;
import br.com.crux.dao.repository.DependentesRepository;
import br.com.crux.entity.Dependentes;
import br.com.crux.to.DependentesTO;

@Component
public class GetDependentesCmd {

	@Autowired private DependentesRepository repository;
	@Autowired private DependentesTOBuilder toBuilder;
	
	public DependentesTO getByCPF(Long cpf) {
		
		Optional<List<Dependentes>> entitys = repository.findByCPF(cpf);
		if(!entitys.isPresent()) {return null;}
		
		List<Dependentes> dependentes = entitys.get();
		Optional<Dependentes> dependenteOptional = dependentes.stream()
													          .filter(d -> Objects.isNull(d.getDataDesligamento()) || d.getDataDesligamento().isBefore(LocalDateTime.now()) )
													          .findFirst();
		if(dependenteOptional.isPresent()) {
			return toBuilder.buildTO(dependenteOptional.get());
		}
		
		return null;
	}

	
}
