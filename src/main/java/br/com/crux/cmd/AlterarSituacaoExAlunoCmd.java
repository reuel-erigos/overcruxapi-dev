package br.com.crux.cmd;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.SituacaoExAlunoTOBuilder;
import br.com.crux.dao.repository.SituacaoExAlunoRepository;
import br.com.crux.entity.SituacaoExAluno;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosSituacaoExAlunoRule;
import br.com.crux.to.SituacaoExAlunoTO;

@Component
public class AlterarSituacaoExAlunoCmd {
	
	@Autowired private SituacaoExAlunoRepository repository;
	@Autowired private SituacaoExAlunoTOBuilder toBuilder;
	@Autowired private CamposObrigatoriosSituacaoExAlunoRule camposObrigatoriosRule;
	
	
	public SituacaoExAlunoTO alterar(SituacaoExAlunoTO to) {
		Optional<SituacaoExAluno> entityOptional = repository.findById(to.getId());
		
		if(!entityOptional.isPresent()) {
			throw new NotFoundException("Situação ex aluno informada não existe.");
		}
		
		camposObrigatoriosRule.verificar(to);
		SituacaoExAluno entity = entityOptional.get();
		entity = toBuilder.build(to);
		return toBuilder.buildTO(repository.save(entity));
		
	}
}
