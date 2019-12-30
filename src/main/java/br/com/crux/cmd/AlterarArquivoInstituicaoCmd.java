package br.com.crux.cmd;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.crux.builder.ArquivoBuilder;
import br.com.crux.dao.repository.ArquivoRepository;
import br.com.crux.entity.Arquivo;

@Component
public class AlterarArquivoInstituicaoCmd {

	@Autowired private ArquivoRepository arquivoRepository;
	@Autowired private ArquivoBuilder arquivoBuilder;
	@Autowired private GravarArquivoInstituicaoCmd gravarArquivoInstituicaoCmd;

	public void alterar(MultipartFile file, Long id) {
		Optional<Arquivo> optionalArquivo = arquivoRepository.findByIdInstituicao(id);
		
		if (optionalArquivo.isPresent()) {
			arquivoBuilder.build(file, optionalArquivo.get());
		} else {
			gravarArquivoInstituicaoCmd.gravar(file, id);
		}
	}
}
