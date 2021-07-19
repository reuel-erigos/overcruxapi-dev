package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.MovimentacoesContabeisTOBuilder;
import br.com.crux.dao.repository.MovimentacoesContabeisRepository;
import br.com.crux.entity.MovimentacoesContabeis;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosMovimentacoesContabeisRule;
import br.com.crux.to.MovimentacoesContabeisTO;

@Component
public class AlterarMovimentacoesContabeisCmd {
	
	@Autowired private MovimentacoesContabeisRepository repository;
	@Autowired private MovimentacoesContabeisTOBuilder toBuilder;
	@Autowired private CamposObrigatoriosMovimentacoesContabeisRule rule;

	public void alterar(MovimentacoesContabeisTO to) {
		MovimentacoesContabeis entity = repository.findById(to.getId()).orElseThrow(() -> new NotFoundException("Entidade informada não existe."));

		rule.verificar(to);
		entity = toBuilder.build(to);
		repository.save(entity);
	}

}
