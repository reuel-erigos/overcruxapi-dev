package br.com.crux.cmd;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.ArquivoBuilder;
import br.com.crux.dao.repository.ArquivoRepository;
import br.com.crux.entity.Arquivo;
import br.com.crux.to.ArquivoTO;

@Component
public class GetArquivoCmd {

	@Autowired private ArquivoRepository arquivoRepository;
	@Autowired private ArquivoBuilder  arquivoBuilder;

	public byte[] get(Long idArquivoMetadados) {
		Optional<Arquivo> arquivo = arquivoRepository.findByIdMetadados(idArquivoMetadados);
		if (!arquivo.isPresent()) {return null;}
		return arquivo.get().getBlob();
	}
	
	
	public ArquivoTO getDados(Long idArquivoMetadados) {
		Optional<Arquivo> arquivo = arquivoRepository.findByIdMetadados(idArquivoMetadados);
		if (!arquivo.isPresent()) {return null;}
		return arquivoBuilder.buildTO(arquivo.get());
	}
}
