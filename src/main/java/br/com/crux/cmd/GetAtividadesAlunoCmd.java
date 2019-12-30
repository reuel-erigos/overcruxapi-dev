package br.com.crux.cmd;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.AtividadesAlunoTOBuilder;
import br.com.crux.dao.repository.AtividadesAlunoRepository;
import br.com.crux.entity.AtividadesAluno;
import br.com.crux.exception.NotFoundException;
import br.com.crux.infra.util.Java8DateUtil;
import br.com.crux.to.AtividadesAlunoTO;

@Component
public class GetAtividadesAlunoCmd {

	@Autowired private AtividadesAlunoRepository repository;
	@Autowired private AtividadesAlunoTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	@Autowired private GetAlunoCmd getAlunoCmd;

	
	public List<AtividadesAlunoTO> getAllAlunosMatriculadosNaAtividade(Long idAtividade) {
		return getAllFilter(null, null, idAtividade).stream().collect(Collectors.toList());
	}
	
	
	public List<AtividadesAlunoTO> getAllFilter(Long idTurma, Long idAluno, Long idAtividade) {
		Long idUnidade = null;
		if(Objects.isNull(idAluno)) {
			idUnidade = getUnidadeLogadaCmd.get().getId();
		} else {
			idUnidade = getAlunoCmd.getTOById(idAluno).getUnidade().getIdUnidade();
		}
		
		Optional<List<AtividadesAluno>> entitys = Optional.empty();
		
		if(Objects.isNull(idTurma) && Objects.isNull(idAluno) && Objects.isNull(idAtividade)) {
			entitys = repository.findByUnidade(idUnidade);	
		
		} else if(Objects.isNull(idTurma) && Objects.isNull(idAluno) && Objects.nonNull(idAtividade)) {
			entitys = repository.findByAtividade(idAtividade);
		
		} else if(Objects.isNull(idTurma) && Objects.nonNull(idAluno)  && Objects.isNull(idAtividade)) {
			entitys = repository.findByAluno(idAluno);

		} else if(Objects.nonNull(idTurma) && Objects.isNull(idAluno)  && Objects.isNull(idAtividade)) {
			entitys = repository.findByTurma(idTurma);
			
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
		
		return new ArrayList<AtividadesAlunoTO>();
	}

	public AtividadesAlunoTO getTOById(Long id) {
		AtividadesAluno entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Atividade de Aluno não encontrada."));
		return toBuilder.buildTO(entity);
	}

	public AtividadesAluno getById(Long id) {
		return repository.findById(id).orElseGet(null);
	}

	public List<AtividadesAlunoTO> getByAluno(Long id) {
		Optional<List<AtividadesAluno>> entitys = repository.findByAluno(id);
		if (!entitys.isPresent()) {
			return Collections.emptyList();
		}
		return toBuilder.buildAll(entitys.get());
	}

	public List<AtividadesAlunoTO> getByAtividade(Long id) {
		Optional<List<AtividadesAluno>> entitys = repository.findByAtividade(id);
		if (!entitys.isPresent()) {
			return Collections.emptyList();
		}
		return toBuilder.buildAll(entitys.get());
	}

	
	public List<AtividadesAlunoTO> getByAlunoEAtividadeEPeriodo(Long idAluno, Long idAtividade, Long dataLong) {
		LocalDate dataReferencia = Java8DateUtil.getLocalDate(new Date(dataLong));
		
		List<AtividadesAlunoTO> atividadesAlunos = getByAlunoEAtividade(idAluno, idAtividade);
		List<AtividadesAlunoTO> resultado = atividadesAlunos.stream().filter( r -> {
			return Java8DateUtil.isVigente(dataReferencia, r.getDataInicioAtividade().toLocalDate(), (Objects.nonNull(r.getDataDesvinculacao()) ? r.getDataDesvinculacao().toLocalDate() : null) );
		}).collect(Collectors.toList());
		
		return resultado;
	}
	
	public List<AtividadesAlunoTO> getByAlunoEAtividade(Long idAluno, Long idAtividade) {
		Optional<List<AtividadesAluno>> entitys = repository.findByAlunoAndAtividade(idAluno,idAtividade);
		if (!entitys.isPresent()) {
			return Collections.emptyList();
		}

		return toBuilder.buildAll(entitys.get());
	}

}
