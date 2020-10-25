package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.AlunoTOBuilder;
import br.com.crux.dao.AlunoDao;
import br.com.crux.dao.dto.AlunoDTO;
import br.com.crux.dao.repository.AlunoRepository;
import br.com.crux.entity.Aluno;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.AlunoTO;
import br.com.crux.to.ComboAlunoTO;

@Component
public class GetAlunoCmd {

	@Autowired private AlunoRepository repository;
	@Autowired private AlunoTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	@Autowired private AlunoDao alunoDao;
	
	public List<ComboAlunoTO> getAllAlunosByCombo() {
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		List<AlunoDTO> alunos = alunoDao.getAllByInstituicao(idInstituicao);
		
		return toBuilder.buildAllDTO(alunos);
	}

	public List<AlunoTO> getAllFilter(Long idAluno, String nomePessoaFisicaMae, String cpfPessoaFisicaAluno) {
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();

		Optional<List<Aluno>> entitys = Optional.empty();

		idAluno              = Objects.isNull(idAluno) ? null : idAluno;
		nomePessoaFisicaMae  = StringUtils.isEmpty(nomePessoaFisicaMae) ? null : nomePessoaFisicaMae;
		cpfPessoaFisicaAluno = StringUtils.isEmpty(cpfPessoaFisicaAluno) ? null : cpfPessoaFisicaAluno;
		cpfPessoaFisicaAluno = StringUtils.isNotEmpty(cpfPessoaFisicaAluno) && cpfPessoaFisicaAluno.equals("000.000.000-00") ? "0" : cpfPessoaFisicaAluno;

		entitys = repository.findByFilter(idInstituicao, idAluno, nomePessoaFisicaMae, cpfPessoaFisicaAluno);
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
