package br.com.crux.cmd;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.dao.repository.ArquivoRepository;
import br.com.crux.dao.repository.PessoaFisicaRepository;
import br.com.crux.entity.Arquivo;
import br.com.crux.entity.PessoaFisica;
import br.com.crux.exception.ParametroNaoInformadoException;
import br.com.crux.exception.PessoaFisicaNaoEncontradaException;

@Component
public class GetArquivoPessoaFisicaCmd {

	@Autowired private ArquivoRepository arquivoRepository;
	@Autowired private PessoaFisicaRepository pessoaFisicaRepository;

	public byte[] getPorPessoa(Long idPessoaFisica) {
		if (Objects.isNull(idPessoaFisica)) {
			throw new ParametroNaoInformadoException("Erro ao buscar o arquivo, id da Pessoa não informado no resource.");
		}

		PessoaFisica pessoa = pessoaFisicaRepository.findById(idPessoaFisica).orElseThrow(() -> new PessoaFisicaNaoEncontradaException("Pessoa não encontrada"));

		if (pessoa.getIdArquivo() == null) {
			return null;
		}

		Optional<Arquivo> arquivo = arquivoRepository.findById(pessoa.getIdArquivo());
		if (!arquivo.isPresent()) {
			return null;
		}

		return arquivo.get().getBlob();

	}

}
