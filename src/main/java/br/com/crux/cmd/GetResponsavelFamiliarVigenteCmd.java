package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ResponsaveisAlunoTOBuilder;
import br.com.crux.dao.repository.ResponsaveisAlunoRepository;
import br.com.crux.entity.Aluno;
import br.com.crux.entity.ResponsaveisAluno;
import br.com.crux.exception.NotFoundException;
import br.com.crux.infra.util.Java8DateUtil;
import br.com.crux.to.ResponsaveisAlunoTO;

@Component
public class GetResponsavelFamiliarVigenteCmd {

	@Autowired private ResponsaveisAlunoRepository responsaveisAlunoRepository;
	@Autowired private ResponsaveisAlunoTOBuilder responsaveisAlunoTOBuilder;
	@Autowired private GetAlunoCmd getAlunoCmd;
	
	public List<ResponsaveisAlunoTO> getAllResponsaveisTO(Long idAluno) {
		Aluno aluno = getAlunoCmd.getById(idAluno);
		if(Objects.isNull(aluno)) {
			throw new NotFoundException("Aluno informado não existe.");
		}
		
		Optional<List<ResponsaveisAluno>> responsavelVigente = responsaveisAlunoRepository.findAllResponsaveisPorAlunoAndUnidade(idAluno, aluno.getUnidade().getIdUnidade());
		if(responsavelVigente.isPresent()) {
			return responsaveisAlunoTOBuilder.buildAll(responsavelVigente.get());
		}
		return new ArrayList<ResponsaveisAlunoTO>();
	}
	
	public ResponsaveisAlunoTO getResponsavelVigente(Long idAluno) {
		List<ResponsaveisAlunoTO> responsaveisTO = getAllResponsaveisTO(idAluno);
		
		Optional<ResponsaveisAlunoTO> vigente = responsaveisTO.stream().filter( r -> {
			return Java8DateUtil.isVigente( r.getDataVinculacao().toLocalDate(), (Objects.nonNull(r.getDataDesvinculacao()) ? r.getDataDesvinculacao().toLocalDate() : null) );
		}).findFirst();
		
		return vigente.orElse(null);
	}
	
	
	public List<ResponsaveisAluno> getAllResponsaveis(Long idAluno, Long idFamiliar) {
		Aluno aluno = getAlunoCmd.getById(idAluno);
		if(Objects.isNull(aluno)) {
			throw new NotFoundException("Aluno informado não existe.");
		}
		
		Optional<List<ResponsaveisAluno>> responsavelVigente = responsaveisAlunoRepository.findByUnidadeAndFamiliarAndAluno(aluno.getUnidade().getIdUnidade(), idFamiliar, idAluno);
		if(responsavelVigente.isPresent()) {
			return responsavelVigente.get();
		}
		return new ArrayList<ResponsaveisAluno>();
	}
}
