package br.com.crux.exception;

import br.com.crux.exception.base.NegocioException;

public class UploadArquivoException extends NegocioException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UploadArquivoException(String msg) {
		super(msg);
	}

}
