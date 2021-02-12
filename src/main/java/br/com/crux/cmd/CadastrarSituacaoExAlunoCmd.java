package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.SituacaoExAlunoTOBuilder;
import br.com.crux.dao.repository.SituacaoExAlunoRepository;
import br.com.crux.entity.SituacaoExAluno;
import br.com.crux.rule.CamposObrigatoriosSituacaoExAlunoRule;
import br.com.crux.to.SituacaoExAlunoTO;

@Component
public class CadastrarSituacaoExAlunoCmd {
	
	@Autowired private SituacaoExAlunoRepository repository;
	@Autowired private SituacaoExAlunoTOBuilder toBuilder;
	@Autowired private CamposObrigatoriosSituacaoExAlunoRule camposObrigatoriosRule;
	
	
	public SituacaoExAlunoTO cadastrar(SituacaoExAlunoTO to) {
		camposObrigatoriosRule.verificar(to);
		SituacaoExAluno entity = toBuilder.build(to);
		return toBuilder.buildTO(repository.save(entity));
	}
}
