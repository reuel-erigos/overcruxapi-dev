package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.AlunoTOBuilder;
import br.com.crux.dao.repository.AlunoRepository;
import br.com.crux.entity.Aluno;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.AlunoTO;

@Component
public class GetAlunoCmd {

	@Autowired private AlunoRepository repository;
	@Autowired private AlunoTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;

	public List<AlunoTO> getAllFilter(Long idAluno, Long idPessoaFisicaMae, String cpfPessoaFisicaAluno) {
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();

		Optional<List<Aluno>> entitys = Optional.empty();

		idAluno              = Objects.isNull(idAluno) ? null : idAluno;
		idPessoaFisicaMae    = Objects.isNull(idPessoaFisicaMae) ? null : idPessoaFisicaMae;
		cpfPessoaFisicaAluno = StringUtils.isEmpty(cpfPessoaFisicaAluno) ? null : cpfPessoaFisicaAluno;
		cpfPessoaFisicaAluno = cpfPessoaFisicaAluno.equals("00000000000") ? null : cpfPessoaFisicaAluno;

		entitys = repository.findByFilter(idInstituicao, idAluno, idPessoaFisicaMae, cpfPessoaFisicaAluno);
		if (entitys.isPresent()) {
			List<AlunoTO> alunos = toBuilder.buildAll(entitys.get());
			return alunos;
		}
		return new ArrayList<AlunoTO>();
	}

	public List<AlunoTO> getAll() {
		Long idUnidade = getUnidadeLogadaCmd.get().getId();
		Optional<List<Aluno>> entitys = repository.findByUnidade(idUnidade);
		if (entitys.isPresent()) {
			return toBuilder.buildAll(entitys.get());
		}
		return new ArrayList<AlunoTO>();
	}

	public AlunoTO getTOById(Long id) {
		Aluno entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Aluno não encontrada."));
		return toBuilder.buildTO(entity);
	}

	public Aluno getById(Long id) {
		return repository.findById(id).orElseGet(null);
	}

	public List<AlunoTO> getAlunosByNome(String nome) {
		Optional<List<Aluno>> entitys = repository.findAlunosByNome(nome.toUpperCase());
		if (entitys.isPresent()) {
			return toBuilder.buildAll(entitys.get());
		}
		return new ArrayList<AlunoTO>();
	}

}
