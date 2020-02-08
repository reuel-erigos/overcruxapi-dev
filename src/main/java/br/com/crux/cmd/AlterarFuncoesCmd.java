package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.FuncoesTOBuilder;
import br.com.crux.dao.repository.FuncoesRepository;
import br.com.crux.entity.Funcoes;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosFuncoesRule;
import br.com.crux.to.FuncoesTO;

@Component
public class AlterarFuncoesCmd {

	@Autowired private FuncoesRepository repository;
	@Autowired private CamposObrigatoriosFuncoesRule rule;
	@Autowired private FuncoesTOBuilder toBuilder;

	public void alterar(FuncoesTO to) {
		Funcoes entity = repository.findById(to.getId())
				.orElseThrow(() -> new NotFoundException("Entidade informada não existe."));

		rule.verificar(to);

		entity = toBuilder.build(to);

		repository.save(entity);

	}

}
