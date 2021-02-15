package br.com.crux.cmd;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.crux.builder.ArquivoBuilder;
import br.com.crux.dao.repository.ArquivoMetadadosRepository;
import br.com.crux.dao.repository.ArquivoRepository;
import br.com.crux.dao.repository.PessoaFisicaRepository;
import br.com.crux.entity.Arquivo;
import br.com.crux.entity.ArquivoMetadado;
import br.com.crux.entity.PessoaFisica;
import br.com.crux.exception.NotFoundException;

@Component
public class SalvarArquivoPessoaFisicaCmd {

	@Autowired private ArquivoRepository arquivoRepository;
	@Autowired private ArquivoMetadadosRepository arquivoMetadadosRepository;
	@Autowired private PessoaFisicaRepository pessoaFisicaRepository;
	@Autowired private ArquivoBuilder arquivoBuilder;
	
	public void salvar(MultipartFile file, Long idPessoaFisica) {
		PessoaFisica pessoa = pessoaFisicaRepository.findById(idPessoaFisica).orElseThrow(() -> new NotFoundException("Pessoa Física não encontrado."));

		Arquivo arquivo = null;		
		if(Objects.nonNull(pessoa.getMetadados())) {
			arquivo = arquivoRepository.findByIdMetadados(pessoa.getMetadados().getId()).get();
			arquivo = arquivoBuilder.build(file, arquivo);
		} else {
			arquivo = arquivoBuilder.build(file);
		}
		
		arquivo = arquivoRepository.save(arquivo);
		ArquivoMetadado metadado = arquivoMetadadosRepository.save(arquivo.getMetadados());

		pessoa.setMetadados(metadado);
		pessoaFisicaRepository.save(pessoa);
	}

}
