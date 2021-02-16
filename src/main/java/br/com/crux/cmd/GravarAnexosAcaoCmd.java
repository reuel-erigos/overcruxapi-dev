package br.com.crux.cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.crux.builder.ArquivoBuilder;
import br.com.crux.builder.ArquivoMetadadosTOBuilder;
import br.com.crux.entity.Arquivo;
import br.com.crux.entity.ArquivoMetadado;
import br.com.crux.to.ArquivoMetadadoTO;

@Component
public class GravarAnexosAcaoCmd {

	@Autowired private GravarArquivoCmd gravarArquivoCmd;
	@Autowired private ArquivoBuilder arquivoBuilder;
	@Autowired private ArquivoMetadadosTOBuilder arquivoMetadadosTOBuilder;

	public ArquivoMetadadoTO salvar(MultipartFile file) {
		Arquivo arquivo = arquivoBuilder.build(file);
		ArquivoMetadado arquivoMetadado = gravarArquivoCmd.gravar(arquivo);
		return arquivoMetadadosTOBuilder.buildTO(arquivoMetadado);
	}

}
