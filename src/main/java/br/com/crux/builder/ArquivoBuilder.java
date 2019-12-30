package br.com.crux.builder;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.Arquivo;
import br.com.crux.exception.UploadArquivoException;
import br.com.crux.infra.util.MD5Util;

@Component
public class ArquivoBuilder {

	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	public Arquivo build(MultipartFile file) {
		Arquivo arquivo = new Arquivo();
		return build(file, arquivo);

	}

	public Arquivo build(MultipartFile file, Arquivo arquivo) {
		try {
			String hashArquivo = MD5Util.getHashArquivo(file.getBytes());

			arquivo.setBlob(file.getBytes());
			arquivo.setDtCriacao(new Date());
			arquivo.setHash(hashArquivo);
			arquivo.setNmArquivo(file.getOriginalFilename());
			arquivo.setNrTamanhoArquivo(file.getSize());
			arquivo.setDsTipoArquivo(file.getContentType());

			Long idUsuarioLogado = getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario();
			arquivo.setUsuarioAlteracao(idUsuarioLogado);

			return arquivo;

		} catch (IOException e) {
			e.printStackTrace();
			throw new UploadArquivoException("Erro ao gravar o arquivo. " + e.getMessage());
		}
	}



}
