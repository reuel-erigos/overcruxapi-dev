package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
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

	public List<AlunosTurmaTO> getAllFilter(Long idTurma, Long idAluno, Long idAtividade) {
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		
		Optional<List<AlunosTurma>> entitys = repository.filter(idTurma, idAluno, idAtividade, idInstituicao);
		if(entitys.isPresent()) {
			List<AlunosTurma> lista = entitys.get().stream().distinct().collect(Collectors.toList());
			return toBuilder.buildAll(lista);
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
