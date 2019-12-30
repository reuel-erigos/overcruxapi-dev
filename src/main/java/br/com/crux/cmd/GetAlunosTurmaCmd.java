package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.AlunosTurmaTOBuilder;
import br.com.crux.dao.repository.AlunosTurmaRepository;
import br.com.crux.entity.AlunosTurma;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.AlunosTurmaTO;

@Component
public class GetAlunosTurmaCmd {

	@Autowired private AlunosTurmaRepository repository;
	@Autowired private AlunosTurmaTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;

	
	public List<AlunosTurmaTO> getAll() {
		return getAllFilter(null, null, null).stream().collect(Collectors.toList());
	}
	
	public List<AlunosTurmaTO> getAllFilter(Long idTurma, Long idAluno, Long idAtividade) {
		Long idUnidade = getUnidadeLogadaCmd.get().getId();
		
		Optional<List<AlunosTurma>> entitys = Optional.empty();
		
		if(Objects.isNull(idTurma) && Objects.isNull(idAluno) && Objects.isNull(idAtividade)) {
			entitys = repository.findByUnidade(idUnidade);	

		} else if(Objects.nonNull(idTurma) && Objects.isNull(idAluno) && Objects.isNull(idAtividade)) {
			entitys = repository.findByTurma(idTurma);
			
		} else if(Objects.isNull(idTurma) && Objects.isNull(idAluno) && Objects.nonNull(idAtividade)) {
			entitys = repository.findByAtividade(idAtividade);
		
		} else if(Objects.isNull(idTurma) && Objects.isNull(idAtividade)  && Objects.nonNull(idAluno)) {
			entitys = repository.findByAluno(idAluno);

		} else if(Objects.isNull(idTurma) && Objects.nonNull(idAluno)  && Objects.nonNull(idAtividade)) {
			entitys = repository.findByAlunoAndAtividade(idAluno, idAtividade);

		} else if(Objects.nonNull(idTurma) && Objects.isNull(idAluno) && Objects.nonNull(idAtividade)) {
			entitys = repository.findByTurmaAndAtividade(idTurma, idAtividade);
			
		} else if(Objects.nonNull(idTurma) && Objects.nonNull(idAluno) && Objects.isNull(idAtividade)) {
			entitys = repository.findByTurmaAndAluno(idTurma, idAluno);
		
		} else if(Objects.nonNull(idTurma) && Objects.nonNull(idAluno) && Objects.nonNull(idAtividade)) {
			entitys = repository.findByTurmaAndAlunoAndAtividade(idAluno, idAtividade, idUnidade);
		}
		
		if(entitys.isPresent()) {
			return toBuilder.buildAll(entitys.get());
		}
		
		return new ArrayList<AlunosTurmaTO>();
	}

	
	
	public AlunosTurmaTO getTOById(Long id) {
		AlunosTurma entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Alunos da turma não encontrado."));
		return toBuilder.buildTO(entity);
	}

	public AlunosTurma getById(Long id) {
		return repository.findById(id).orElseGet(null);
	}


}
