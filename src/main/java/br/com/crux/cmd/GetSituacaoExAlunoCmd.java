				
package br.com.crux.cmd;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.SituacaoExAlunoTOBuilder;
import br.com.crux.dao.SituacaoExAlunoDAO;
import br.com.crux.dao.dto.ComboSituacaoExAlunoDTO;
import br.com.crux.dao.repository.SituacaoExAlunoRepository;
import br.com.crux.entity.SituacaoExAluno;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.ComboSituacaoExAlunoTO;
import br.com.crux.to.SituacaoExAlunoTO;

@Component
public class GetSituacaoExAlunoCmd {

	@Autowired private SituacaoExAlunoRepository repository;
	@Autowired private SituacaoExAlunoTOBuilder toBuilder;
	@Autowired private SituacaoExAlunoDAO situacaoExAlunoDAO;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;

	public List<SituacaoExAlunoTO> getAll() {
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		Optional<List<SituacaoExAluno>> alunos = repository.getTodosAlunos(idInstituicao);
		if(alunos.isPresent()) {
			return toBuilder.buildTO(alunos.get());
		}
		return Collections.emptyList();
	}

	public SituacaoExAluno getById(Long id) {
		return repository.findById(id).orElse(null);
	}

	public SituacaoExAlunoTO getTOById(Long id) {
		SituacaoExAluno entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Situação do ex aluno não encontrada."));
		return toBuilder.buildTO(entity);
	}
	
	public List<ComboSituacaoExAlunoTO> getAllByCombo() {
		Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
		List<ComboSituacaoExAlunoDTO> situacao = situacaoExAlunoDAO.getAll(idInstituicao);
		
		return toBuilder.buildAllDTO(situacao);
	}
}
