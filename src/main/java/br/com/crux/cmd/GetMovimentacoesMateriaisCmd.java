package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.MovimentacoesMateriaisTOBuilder;
import br.com.crux.dao.repository.MovimentacoesMateriaisRepository;
import br.com.crux.entity.MovimentacoesMateriais;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.MovimentacoesMateriaisTO;

@Component
public class GetMovimentacoesMateriaisCmd {

	@Autowired private MovimentacoesMateriaisRepository repository;
	@Autowired private MovimentacoesMateriaisTOBuilder toBuilder;
	
	public List<MovimentacoesMateriaisTO> getAll() {
	
		List<MovimentacoesMateriais> entitys = repository.findAll();
		if(!entitys.isEmpty()) {
			return toBuilder.buildAll(entitys);
		}
		return new ArrayList<MovimentacoesMateriaisTO>();
		
	}
	
	public MovimentacoesMateriaisTO getTOById(Long id) {
		MovimentacoesMateriais entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Entidade não encontrada"));
		return toBuilder.buildTO(entity);
	}
	
	public MovimentacoesMateriais getById(Long id) {
		return repository.findById(id).orElseGet(null);
	}
			
}
