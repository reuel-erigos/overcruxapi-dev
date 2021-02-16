package br.com.crux.cmd;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.crux.builder.ArquivoBuilder;
import br.com.crux.dao.repository.ArquivoMetadadosRepository;
import br.com.crux.dao.repository.ArquivoRepository;
import br.com.crux.entity.Arquivo;
import br.com.crux.entity.ArquivoMetadado;

@Component
public class GravarArquivoCmd {

	@Autowired private ArquivoBuilder arquivoBuilder;
	@Autowired private ArquivoRepository arquivoRepository;
	@Autowired private ArquivoMetadadosRepository arquivoMetadadosRepository;
	
	public ArquivoMetadado gravar(Arquivo arquivo) {
		arquivo = arquivoRepository.save(arquivo);
		ArquivoMetadado metadado = arquivoMetadadosRepository.save(arquivo.getMetadados());
		return metadado;
	}
	
	
	public Arquivo build(MultipartFile file, Arquivo arquivo) {
		if(Objects.nonNull(arquivo.getMetadados())) {
			arquivo = arquivoRepository.findByIdMetadados(arquivo.getMetadados().getId()).get();
			arquivo = arquivoBuilder.build(file, arquivo);
		} else {
			arquivo = arquivoBuilder.build(file);
		}
		return arquivo;
	}
}
