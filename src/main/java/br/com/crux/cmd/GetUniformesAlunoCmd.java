package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.UniformesAlunoTOBuilder;
import br.com.crux.dao.repository.UniformesAlunoRepository;
import br.com.crux.entity.UniformesAluno;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.UniformesAlunoTO;

@Component
public class GetUniformesAlunoCmd {

	@Autowired private UniformesAlunoRepository repository;
	@Autowired private UniformesAlunoTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	@Autowired private GetAlunoCmd getAlunoCmd;

	public List<UniformesAlunoTO> getAllAlunosMatriculadosTO(Long idAtividade) {
		return toBuilder.buildAll(getAllFilter(null, idAtividade));
	}

	public List<UniformesAluno> getAllAlunosMatriculados(Long idAtividade) {
		return getAllFilter(null, idAtividade);
	}

	public List<UniformesAluno> getAllFilter(Long idAluno, Long idAtividade) {
		Long idUnidade = null;

		if (Objects.isNull(idAluno)) {
			idUnidade = getUnidadeLogadaCmd.get().getId();
		} else {
			idUnidade = getAlunoCmd.getTOById(idAluno).getUnidade().getIdUnidade();
		}
		Optional<List<UniformesAluno>> entitys = Optional.empty();

		if (Objects.isNull(idAluno) && Objects.isNull(idAtividade)) {
			entitys = repository.findByUnidade(idUnidade);
		} else if (Objects.isNull(idAluno) && Objects.nonNull(idAtividade)) {
			entitys = repository.findByAtividade(idAtividade);
		} else if (Objects.isNull(idAtividade) && Objects.nonNull(idAluno)) {
			entitys = repository.findByAluno(idAluno);
		} else if (Objects.nonNull(idAluno) && Objects.nonNull(idAtividade)) {
			entitys = repository.findByAlunoAndAtividade(idAluno, idAtividade);
		}

		if (entitys.isPresent()) {
			return entitys.get();
		}
		return new ArrayList<UniformesAluno>();
	}

	public UniformesAlunoTO getById(Long id) {
		Optional<UniformesAluno> entityOptional = repository.findById(id);
		if (!entityOptional.isPresent()) {
			throw new NotFoundException("Uniforme do aluno não encontrada.");
		}
		return toBuilder.buildTO(entityOptional.get());
	}

}
