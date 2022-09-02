package br.com.crux.cmd;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.crux.builder.AlunoTOBuilder;
import br.com.crux.dao.AlunoDao;
import br.com.crux.dao.dto.ComboAlunoDTO;
import br.com.crux.dao.repository.AlunoRepository;
import br.com.crux.dao.spec.AlunoSpec;
import br.com.crux.entity.Aluno;
import br.com.crux.exception.NotFoundException;
import br.com.crux.infra.util.Java8DateUtil;
import br.com.crux.to.AlunoTO;
import br.com.crux.to.ComboAlunoTO;
import br.com.crux.to.filtro.FiltroAlunoTO;

@Component
public class GetAlunoCmd {

	@Autowired private AlunoRepository repository;
	@Autowired private AlunoDao dao;
	@Autowired private AlunoTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	
	public List<ComboAlunoTO> getAllAlunosByCombo() {
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		List<ComboAlunoDTO> alunos = dao.getAllByInstituicao(idInstituicao);
		
		return toBuilder.buildAllDTO(alunos);
	}

	public List<AlunoTO> getAllFilter(Long idAluno, String nomePessoaFisicaMae, String cpfPessoaFisicaAluno,
			Long dataInicioEntradaInstituicao, Long dataFimEntradaInstituicao) {
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();

		Optional<List<AlunoTO>> entitys = Optional.empty();

		idAluno              = Objects.isNull(idAluno) ? null : idAluno;
		nomePessoaFisicaMae  = StringUtils.isEmpty(nomePessoaFisicaMae) ? null : nomePessoaFisicaMae;
		cpfPessoaFisicaAluno = StringUtils.isEmpty(cpfPessoaFisicaAluno) ? null : cpfPessoaFisicaAluno;
		cpfPessoaFisicaAluno = StringUtils.isNotEmpty(cpfPessoaFisicaAluno) && cpfPessoaFisicaAluno.equals("000.000.000-00") ? "0" : cpfPessoaFisicaAluno;
		
		LocalDate pDataInicioEntradaInstituicao  = Objects.nonNull(dataInicioEntradaInstituicao) ? Java8DateUtil.getLocalDateTime(new Date(dataInicioEntradaInstituicao)).toLocalDate() : null;
		LocalDate pDataFimEntradaInstituicao     = Objects.nonNull(dataFimEntradaInstituicao) ? Java8DateUtil.getLocalDateTime(new Date(dataFimEntradaInstituicao)).toLocalDate() : null;
		
		entitys = dao.getAllFilter(idInstituicao, idAluno, nomePessoaFisicaMae, cpfPessoaFisicaAluno, pDataInicioEntradaInstituicao, pDataFimEntradaInstituicao);
		if (entitys.isPresent()) {
			return entitys.get();
		}
		return new ArrayList<AlunoTO>();
	}
	
	@Transactional(readOnly = true)
	public Page<AlunoTO> listFilteredAndPaged(FiltroAlunoTO filtro, Pageable pageable) {
		filtro.setIdUnidade(getUnidadeLogadaCmd.getUnidadeTOSimplificado().getIdUnidade());
		Page<Aluno> pageData = repository.findAll(AlunoSpec.findByCriteria(filtro), pageable);
		final List<AlunoTO> listTO = new ArrayList<AlunoTO>();
		pageData.getContent().forEach(tipoMeta -> listTO.add(toBuilder.toDTOList(tipoMeta)));
		final Page<AlunoTO> pageDataTO = new PageImpl<AlunoTO>(listTO, pageable, pageData.getTotalElements());
		return pageDataTO;
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
