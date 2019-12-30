package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.AlunosTrabalhandoTOBuilder;
import br.com.crux.dao.repository.AlunosTrabalhandoRepository;
import br.com.crux.entity.AlunosTrabalhando;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.AlunosTrabalhandoTO;

@Component
public class GetAlunosTrabalhandoCmd {

	@Autowired private AlunosTrabalhandoRepository repository;
	@Autowired private AlunosTrabalhandoTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	
	
	public List<AlunosTrabalhandoTO> getAll() {
		Long idUnidade = getUnidadeLogadaCmd.get().getId();
		Optional<List<AlunosTrabalhando>> entitys = repository.findByUnidade(idUnidade);
		if(entitys.isPresent()) {
			return toBuilder.buildAll(entitys.get());
		}
		return new ArrayList<AlunosTrabalhandoTO>();
	}
	
	public AlunosTrabalhandoTO getById(Long id) {
		Optional<AlunosTrabalhando> entityOptional = repository.findById(id);
		if(!entityOptional.isPresent()) {
			throw new NotFoundException("Trabalho aluno não encontrado.");
		}
		return toBuilder.buildTO(entityOptional.get());
	}
			
}
