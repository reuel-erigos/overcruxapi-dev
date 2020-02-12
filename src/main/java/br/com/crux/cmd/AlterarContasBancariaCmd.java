package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ContasBancariaTOBuilder;
import br.com.crux.dao.repository.ContasBancariaRepository;
import br.com.crux.entity.ContasBancaria;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosContasBancariaRule;
import br.com.crux.to.ContasBancariaTO;

@Component
public class AlterarContasBancariaCmd {

	@Autowired private ContasBancariaRepository repository;
	@Autowired private ContasBancariaTOBuilder toBuilder;
	@Autowired private CamposObrigatoriosContasBancariaRule rule;

	public void alterar(ContasBancariaTO to) {
		ContasBancaria entity = repository.findById(to.getId())
				.orElseThrow(() -> new NotFoundException("Entidade informada não existe."));

		rule.verificar(to);

		entity = toBuilder.build(to);

		repository.save(entity);

	}

}
