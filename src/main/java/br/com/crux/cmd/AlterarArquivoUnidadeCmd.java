package br.com.crux.cmd;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.crux.builder.ArquivoBuilder;
import br.com.crux.dao.repository.ArquivoRepository;
import br.com.crux.entity.Arquivo;

@Component
public class AlterarArquivoUnidadeCmd {

	@Autowired private ArquivoRepository arquivoRepository;
	@Autowired private ArquivoBuilder arquivoBuilder;
	@Autowired private GravarArquivoUnidadeCmd gravarArquivoUnidadeCmd;

	public void alterar(MultipartFile file, Long idUnidade) {
		Optional<Arquivo> optionalArquivo = arquivoRepository.findByIdUnidade(idUnidade);
		
		if (optionalArquivo.isPresent()) {
			arquivoBuilder.build(file, optionalArquivo.get());
		} else {
			gravarArquivoUnidadeCmd.gravar(file, idUnidade);
		}
	}
}
