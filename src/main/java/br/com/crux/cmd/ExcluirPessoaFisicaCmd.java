package br.com.crux.cmd;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.PessoaFisicaRepository;
import br.com.crux.entity.PessoaFisica;
import br.com.crux.exception.ParametroNaoInformadoException;
import br.com.crux.exception.PessoaFisicaNaoEncontradaException;

@Component
public class ExcluirPessoaFisicaCmd {

	@Autowired private PessoaFisicaRepository repository;
	@Autowired private ExcluirArquivoPessoaFisicaCmd excluirArquivoPessoaFisicaCmd;
	

	public void excluirPorId(Long idPessoa) {
		if (Objects.isNull(idPessoa)) {
			throw new ParametroNaoInformadoException("Identificador da pessoa não informado.");
		}
		
		PessoaFisica pessoa = repository.findById(idPessoa).orElseThrow(() -> new PessoaFisicaNaoEncontradaException("Pessoa informada não existe."));
		
		repository.delete(pessoa);
		
		Optional.ofNullable(pessoa.getIdArquivo()).ifPresent(idArquivo -> excluirArquivoPessoaFisicaCmd.excluirPorId(idArquivo));
	}

	public void excluir(PessoaFisica pessoasFisica) {
		excluirPorId(pessoasFisica.getId());
		
	}
}
