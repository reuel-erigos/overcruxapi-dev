package br.com.crux.builder;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.crux.cmd.GetUnidadeLogadaCmd;
import br.com.crux.entity.ArquivoMetadado;
import br.com.crux.infra.util.MD5Util;
import br.com.crux.to.ArquivoMetadadoTO;

@Component
public class ArquivoMetadadosTOBuilder {
	
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;

	public ArquivoMetadadoTO buildTO(ArquivoMetadado p) {
		ArquivoMetadadoTO retorno = new ArquivoMetadadoTO();
		
		if(Objects.isNull(p)) {
			return retorno;
		}
		
		BeanUtils.copyProperties(p, retorno);
		return retorno;
	}

	public ArquivoMetadado build(ArquivoMetadadoTO to) {
		ArquivoMetadado retorno = new ArquivoMetadado();
		
		BeanUtils.copyProperties(to, retorno);

		return retorno;
	}


	
	public ArquivoMetadado getMetadadosTO(MultipartFile file, ArquivoMetadado metadados) throws IOException {
		String hashArquivo = MD5Util.getHashArquivo(file.getBytes());
		
		metadados.setDtCriacao(LocalDateTime.now());
		metadados.setHash(hashArquivo);
		metadados.setNmArquivo(file.getOriginalFilename());
		metadados.setNrTamanhoArquivo(file.getSize());
		metadados.setDsTipoArquivo(file.getContentType());
		metadados.setIdInstituicao(getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId());
		
		return metadados;
	}

}
