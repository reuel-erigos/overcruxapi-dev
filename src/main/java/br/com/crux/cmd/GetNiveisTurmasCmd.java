package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.NiveisTurmasTOBuilder;
import br.com.crux.dao.repository.NiveisTurmasRepository;
import br.com.crux.entity.NiveisTurmas;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.NiveisTurmasTO;

@Component
public class GetNiveisTurmasCmd {

	@Autowired private NiveisTurmasRepository repository;
	@Autowired private NiveisTurmasTOBuilder toBuilder;
	
	public List<NiveisTurmasTO> getAll() {
		List<NiveisTurmas> entitys = repository.findAll();
		if(Objects.isNull(entitys) || entitys.isEmpty()) {
			return new ArrayList<NiveisTurmasTO>();
		}
		return toBuilder.buildAll(entitys);
	}
	
	public NiveisTurmasTO getTOById(Long id) {
		NiveisTurmas entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Nível da turma não encontrado"));
		return toBuilder.buildTO(entity);
	}
	
	public NiveisTurmas getById(Long id) {
		return repository.findById(id).orElseGet(null);
	}
			
}
