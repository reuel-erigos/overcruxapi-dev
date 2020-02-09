package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.CategoriasContabeisTOBuilder;
import br.com.crux.dao.repository.CategoriasContabeisRepository;
import br.com.crux.entity.CategoriasContabeis;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosCategoriasContabeisRule;
import br.com.crux.to.CategoriasContabeisTO;

@Component
public class AlterarCategoriasContabeisCmd {

	@Autowired private CategoriasContabeisRepository repository;
	@Autowired private CamposObrigatoriosCategoriasContabeisRule rule;
	@Autowired private CategoriasContabeisTOBuilder toBuilder;

	public void alterar(CategoriasContabeisTO to) {
		CategoriasContabeis entity = repository.findById(to.getId())
				.orElseThrow(() -> new NotFoundException("Entidade informada não existe."));

		rule.verificar(to);

		entity = toBuilder.build(to);

		repository.save(entity);

	}

}
