package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.CategoriasContabeisTOBuilder;
import br.com.crux.dao.repository.CategoriasContabeisRepository;
import br.com.crux.entity.CategoriasContabeis;
import br.com.crux.rule.CamposObrigatoriosCategoriasContabeisRule;
import br.com.crux.to.CategoriasContabeisTO;

@Component
public class CadastrarCategoriasContabeisCmd {

	@Autowired private CategoriasContabeisRepository repository;
	@Autowired private CategoriasContabeisTOBuilder toBuilder;
	@Autowired private CamposObrigatoriosCategoriasContabeisRule rule;

	public void cadastrar(CategoriasContabeisTO to) {
		rule.verificar(to);
		CategoriasContabeis entity = toBuilder.build(to);
		repository.save(entity);

	}
}
