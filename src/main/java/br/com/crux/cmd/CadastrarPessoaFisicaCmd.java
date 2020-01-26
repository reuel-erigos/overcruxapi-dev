package br.com.crux.cmd;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.PessoaFisicaTOBuilder;
import br.com.crux.dao.repository.PessoaFisicaRepository;
import br.com.crux.entity.PessoaFisica;
import br.com.crux.exception.NotFoundException;
import br.com.crux.rule.CamposObrigatoriosPessoaFisicaRule;
import br.com.crux.to.PessoaFisicaTO;

@Component
public class CadastrarPessoaFisicaCmd {

	@Autowired private PessoaFisicaRepository repository;
	@Autowired private CamposObrigatoriosPessoaFisicaRule camposObrigatoriosPessoaFisicaRule;
	@Autowired private PessoaFisicaTOBuilder pessoaFisicaTOBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;

	public PessoaFisica cadastrar(PessoaFisicaTO to) {
		camposObrigatoriosPessoaFisicaRule.verificar(to);
		
		/*
		Optional<PessoaFisica> pessoa = repository.findByCpf(to.getCpf());
		if(pessoa.isPresent()) {
			throw new PessoaFisicaJaExisteException("Já existe um registro cadastrado com esse CPF: " + to.getCpf());
		}
		*/
		
		if(Objects.isNull(getUnidadeLogadaCmd.getUnidadeTO().getInstituicao())) {
			throw new NotFoundException("Não foi possível identificar a instituição da unidade logada");
		}
		
		PessoaFisica pessoaFisica = pessoaFisicaTOBuilder.build(to);
		return repository.save(pessoaFisica);
	}
}
