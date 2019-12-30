package br.com.crux.cmd;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.PessoaFisicaTOBuilder;
import br.com.crux.dao.repository.PessoaFisicaRepository;
import br.com.crux.entity.PessoaFisica;
import br.com.crux.exception.PessoaFisicaJaExisteException;
import br.com.crux.rule.CamposObrigatoriosPessoaFisicaRule;
import br.com.crux.to.PessoaFisicaTO;

@Component
public class AlterarPessoaFisicaCmd {

	@Autowired private PessoaFisicaRepository repository;
	@Autowired private CamposObrigatoriosPessoaFisicaRule camposObrigatoriosPessoaFisicaRule;
	@Autowired private PessoaFisicaTOBuilder pessoaFisicaTOBuilder;

	public PessoaFisica alterar(PessoaFisicaTO to) {
		
		camposObrigatoriosPessoaFisicaRule.verificar(to);
		
		/*
		Optional<PessoaFisica> pessoa = repository.findByCpf(to.getCpf());
		if(pessoa.isPresent() && !pessoa.get().getId().equals(to.getId())) {
			throw new PessoaFisicaJaExisteException("Já existe um registro cadastrado com esse CPF: " + to.getCpf());
		}
		*/
		
		PessoaFisica pessoaFisica = pessoaFisicaTOBuilder.build(to);
		
		return repository.save(pessoaFisica);
	}
}
