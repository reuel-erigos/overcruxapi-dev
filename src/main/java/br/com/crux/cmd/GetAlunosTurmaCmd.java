package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import br.com.crux.builder.AlunosTurmaTOBuilder;
import br.com.crux.dao.repository.AlunosTurmaRepository;
import br.com.crux.dao.spec.AlunoTurmaSpec;
import br.com.crux.entity.AlunosTurma;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.AlunosTurmaTO;
import br.com.crux.to.filtro.FiltroAlunoTurmaTO;

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

	@Transactional(readOnly = true)
	public Page<AlunosTurmaTO> listFilteredAndPaged(FiltroAlunoTurmaTO filtro, Pageable pageable) {
		filtro.setIdUnidade(getUnidadeLogadaCmd.getUnidadeTO().getIdUnidade());
		Page<AlunosTurma> pageData = repository.findAll(AlunoTurmaSpec.findByCriteria(filtro), pageable);
		final List<AlunosTurmaTO> listTO = new ArrayList<AlunosTurmaTO>();
		pageData.getContent().forEach(item -> listTO.add(toBuilder.toDTOList(item)));
		final Page<AlunosTurmaTO> pageDataTO = new PageImpl<AlunosTurmaTO>(listTO, pageable, pageData.getTotalElements());
		return pageDataTO;
	}



	public List<AlunosTurmaTO> getTOByAluno(Long idAluno) {
		List<AlunosTurma> list = repository.findByAlunoId(idAluno);
		if(!CollectionUtils.isEmpty(list)) {
			return toBuilder.buildAll(list);
		}
		return null;
	}
}
