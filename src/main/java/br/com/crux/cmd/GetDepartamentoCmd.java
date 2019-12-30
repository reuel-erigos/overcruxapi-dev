package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.DepartamentoTOBuilder;
import br.com.crux.dao.repository.DepartamentoRepository;
import br.com.crux.entity.Departamentos;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.DepartamentoTO;

@Component
public class GetDepartamentoCmd {

	@Autowired private DepartamentoRepository repository;
	@Autowired private DepartamentoTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	
	
	public List<DepartamentoTO> getAll() {
	
		Long idUnidade = getUnidadeLogadaCmd.get().getId();
		Optional<List<Departamentos>> entitys = repository.findByIdUnidade(idUnidade);
		if(entitys.isPresent()) {
			return toBuilder.buildAll(entitys.get());
		}
		return new ArrayList<DepartamentoTO>();
		
	}
	
	public DepartamentoTO getTOById(Long idDepartamento) {
		Departamentos departamento = repository.findById(idDepartamento).orElseThrow(() -> new NotFoundException("Departamento não encontrado"));
		return toBuilder.buildTO(departamento);
	}
	
	public Departamentos getById(Long idDepartamento) {
		return repository.findById(idDepartamento).orElseGet(null);
	}
			
}
