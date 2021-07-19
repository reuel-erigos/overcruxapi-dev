package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ContasBancariaTOBuilder;
import br.com.crux.dao.repository.ContasBancariaRepository;
import br.com.crux.entity.ContasBancaria;
import br.com.crux.rule.CamposObrigatoriosContasBancariaRule;
import br.com.crux.to.ContasBancariaTO;

@Component
public class CadastrarContasBancariaCmd {

	@Autowired private ContasBancariaRepository repository;
	@Autowired private ContasBancariaTOBuilder toBuilder;
	@Autowired private CamposObrigatoriosContasBancariaRule rule;

	public void cadastrar(ContasBancariaTO to) {
		rule.verificar(to);
		ContasBancaria entity = toBuilder.build(to);
		repository.save(entity);
	}
}
