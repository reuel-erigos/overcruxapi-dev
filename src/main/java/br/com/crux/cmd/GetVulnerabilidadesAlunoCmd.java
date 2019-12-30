package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.VulnerabilidadesAlunoTOBuilder;
import br.com.crux.dao.repository.VulnerabilidadesAlunoRepository;
import br.com.crux.entity.Aluno;
import br.com.crux.entity.VulnerabilidadesAluno;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.VulnerabilidadesAlunoTO;

@Component
public class GetVulnerabilidadesAlunoCmd {

	@Autowired private VulnerabilidadesAlunoRepository repository;
	@Autowired private VulnerabilidadesAlunoTOBuilder toBuilder;
	@Autowired private GetAlunoCmd getAlunoCmd;
	
	
	public List<VulnerabilidadesAluno> getAllAluno(Long idAluno) {
		List<VulnerabilidadesAluno> retorno = new ArrayList<VulnerabilidadesAluno>();
		
		Aluno aluno = getAlunoCmd.getById(idAluno);
		if(Objects.isNull(aluno)) {
			throw new NotFoundException("Aluno informado não existe.");
		}
		Long idUnidade = aluno.getUnidade().getIdUnidade();
		
		Optional<List<VulnerabilidadesAluno>> vulnerabilidades = repository.findByUnidadeAndAluno(idUnidade, idAluno);
		if(!vulnerabilidades.isPresent()) {return new ArrayList<VulnerabilidadesAluno>();}
		
		vulnerabilidades.get().stream().forEach(r -> retorno.add(r));
		return retorno;
		
	}
	
	public List<VulnerabilidadesAlunoTO> getAllAlunoTO(Long idAluno) {
		List<VulnerabilidadesAlunoTO> retorno = new ArrayList<VulnerabilidadesAlunoTO>();
		
		List<VulnerabilidadesAluno> vulnerabilidades = getAllAluno(idAluno);
		vulnerabilidades.stream().forEach(r -> retorno.add(toBuilder.buildTO(r)));
		
		return retorno;
	}	
}
