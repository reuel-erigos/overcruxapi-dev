package br.com.crux.cmd;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.crux.builder.ArquivoBuilder;
import br.com.crux.builder.ArquivoMetadadosTOBuilder;
import br.com.crux.dao.repository.ArquivoMetadadosRepository;
import br.com.crux.dao.repository.ArquivoRepository;
import br.com.crux.entity.Arquivo;
import br.com.crux.entity.ArquivoMetadado;
import br.com.crux.to.ArquivoMetadadoTO;

@Component
public class GravarArquivoCmd {
	
	@Autowired private ArquivoBuilder arquivoBuilder;
	@Autowired private ArquivoMetadadosTOBuilder arquivoMetadadosTOBuilder;
	@Autowired private ArquivoMetadadosRepository arquivoMetadadosRepository;
	@Autowired private ArquivoRepository arquivoRepository;
	
	public ArquivoMetadadoTO salvar(MultipartFile file) {
		Arquivo arquivo = arquivoBuilder.build(file);
		ArquivoMetadado arquivoMetadado = gravar(arquivo);
		return arquivoMetadadosTOBuilder.buildTO(arquivoMetadado);
	}

	
	public ArquivoMetadado gravar(Arquivo arquivo) {
		ArquivoMetadado metadado = arquivoMetadadosRepository.save(arquivo.getMetadados());
		
		arquivo.setMetadados(metadado);
		arquivo = arquivoRepository.save(arquivo);
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
