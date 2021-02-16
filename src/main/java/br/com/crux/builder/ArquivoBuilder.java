package br.com.crux.builder;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.Arquivo;
import br.com.crux.entity.ArquivoMetadado;
import br.com.crux.exception.UploadArquivoException;

@Component
public class ArquivoBuilder {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private ArquivoMetadadosTOBuilder arquivoMetadadosTOBuilder;

	public Arquivo build(MultipartFile file) {
		Arquivo arquivo = new Arquivo();
		arquivo.setMetadados(new ArquivoMetadado());
		return build(file, arquivo);
	}

	public Arquivo build(MultipartFile file, Arquivo arquivo) {
		Long idUsuarioLogado = getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario();
		try {
			arquivo.setBlob(file.getBytes());
			arquivo.setUsuarioAlteracao(idUsuarioLogado);
			arquivo.setMetadados(arquivoMetadadosTOBuilder.getMetadadosTO(file, arquivo.getMetadados()));
			arquivo.getMetadados().setUsuarioAlteracao(idUsuarioLogado);

			return arquivo;
		} catch (IOException e) {
			throw new UploadArquivoException("Erro ao recuperar os metadados do arquivo. " + file.getName());
		}
	}




}
