package br.com.crux.exception;

import br.com.crux.exception.base.NegocioException;

public class GruposModuloDuplicadoException extends NegocioException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GruposModuloDuplicadoException(String msg) {
		super(msg);
	}

}
