package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.crux.builder.ArquivoBuilder;
import br.com.crux.dao.repository.ArquivoRepository;
import br.com.crux.dao.repository.PessoaFisicaRepository;
import br.com.crux.entity.Arquivo;
import br.com.crux.entity.PessoaFisica;
import br.com.crux.exception.NotFoundException;

@Component
public class SalvarArquivoPessoaFisicaCmd {

	@Autowired private ArquivoRepository arquivoRepository;
	@Autowired private PessoaFisicaRepository pessoaFisicaRepository;
	@Autowired private ArquivoBuilder arquivoBuilder;

	public void salvar(MultipartFile file, Long idPessoaFisica) {
		Arquivo arquivoSalvo = gravar(file);
		PessoaFisica pessoa = pessoaFisicaRepository.findById(idPessoaFisica).orElseThrow(() -> new NotFoundException("Pessoa Física não encontrado."));
		pessoa.setIdArquivo(arquivoSalvo.getIdArquivo());
	}

	private Arquivo gravar(MultipartFile file) {
		Arquivo arquivo = arquivoBuilder.build(file);
		return arquivoRepository.save(arquivo);

	}

}
