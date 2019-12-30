package br.com.crux.cmd;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.crux.builder.ArquivoBuilder;
import br.com.crux.dao.repository.ArquivoRepository;
import br.com.crux.entity.Arquivo;

@Component
public class AlterarArquivoPessoaFisicaCmd {

	@Autowired private ArquivoRepository arquivoRepository;
	@Autowired private ArquivoBuilder arquivoBuilder;
	@Autowired private SalvarArquivoPessoaFisicaCmd salvarArquivoPessoaFisicaCmd;

	public void alterar(MultipartFile file, Long idPessoaFisica) {
		Optional<Arquivo> optionalArquivo = arquivoRepository.findByIdPessoaFisica(idPessoaFisica);

		if (optionalArquivo.isPresent()) {
			arquivoBuilder.build(file, optionalArquivo.get());
		}else {
			salvarArquivoPessoaFisicaCmd.salvar(file, idPessoaFisica);
		}
	}

}
