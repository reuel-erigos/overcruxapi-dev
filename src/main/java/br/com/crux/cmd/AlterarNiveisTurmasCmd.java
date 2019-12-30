package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.NiveisTurmasTOBuilder;
import br.com.crux.dao.repository.NiveisTurmasRepository;
import br.com.crux.entity.NiveisTurmas;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosNiveisTurmasRule;
import br.com.crux.to.NiveisTurmasTO;

@Component
public class AlterarNiveisTurmasCmd {

	@Autowired private NiveisTurmasRepository repository;
	@Autowired private CamposObrigatoriosNiveisTurmasRule camposObrigatoriosRule;
	@Autowired private NiveisTurmasTOBuilder toBuilder;

	public void alterar(NiveisTurmasTO to) {
		NiveisTurmas entity = repository.findById(to.getId()).orElseThrow(() -> new NotFoundException("Nível da turma informado não existe."));
		camposObrigatoriosRule.verificar(to);
		entity = toBuilder.build(to);
		repository.save(entity);

	}
	
}
