package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.FuncoesTOBuilder;
import br.com.crux.dao.repository.FuncoesRepository;
import br.com.crux.entity.Funcoes;
import br.com.crux.rule.CamposObrigatoriosFuncoesRule;
import br.com.crux.to.FuncoesTO;

@Component
public class CadastrarFuncoesCmd {

	@Autowired private FuncoesRepository repository;
	@Autowired private FuncoesTOBuilder toBuilder;
	@Autowired private CamposObrigatoriosFuncoesRule rule;

	public void cadastrar(FuncoesTO to) {

		rule.verificar(to);

		Funcoes entity = toBuilder.build(to);

		repository.save(entity);

	}
}
