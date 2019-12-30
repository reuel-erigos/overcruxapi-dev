package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.EncaminhaAlunosTOBuilder;
import br.com.crux.dao.repository.EncaminhaAlunosRepository;
import br.com.crux.entity.EncaminhaAlunos;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.EncaminhaAlunosTO;

@Component
public class GetEncaminhaAlunosCmd {

	@Autowired private EncaminhaAlunosRepository repository;
	@Autowired private EncaminhaAlunosTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	@Autowired private GetAlunoCmd getAlunoCmd;

	public List<EncaminhaAlunosTO> getAll(Long idAluno, Long idEntidadeSocial) {
		Long idUnidade = null;
		if (Objects.isNull(idAluno)) {
			idUnidade = getUnidadeLogadaCmd.get().getId();
		} else {
			idUnidade = getAlunoCmd.getTOById(idAluno).getUnidade().getIdUnidade();
		}

		Optional<List<EncaminhaAlunos>> entitys = null;

		if (Objects.nonNull(idAluno) && Objects.nonNull(idEntidadeSocial)) {
			entitys = repository.findByUnidadeAlunoEntidadeSocial(idUnidade, idAluno, idEntidadeSocial);
		} else if (Objects.isNull(idAluno) && Objects.isNull(idEntidadeSocial)) {
			entitys = repository.findByUnidade(idUnidade);
		} else if (Objects.isNull(idAluno) && Objects.nonNull(idEntidadeSocial)) {
			entitys = repository.findByEntidadeSocial(idEntidadeSocial);
		} else if (Objects.nonNull(idAluno) && Objects.isNull(idEntidadeSocial)) {
			entitys = repository.findByUnidadeAluno(idUnidade, idAluno);
		}

		if (entitys.isPresent()) {
			return toBuilder.buildAll(entitys.get());
		}

		return new ArrayList<EncaminhaAlunosTO>();
	}

	public EncaminhaAlunosTO getById(Long id) {
		Optional<EncaminhaAlunos> entityOptional = repository.findById(id);
		if (!entityOptional.isPresent()) {
			throw new NotFoundException("Encaminhamento do aluno não encontrado.");
		}
		return toBuilder.buildTO(entityOptional.get());
	}

}
