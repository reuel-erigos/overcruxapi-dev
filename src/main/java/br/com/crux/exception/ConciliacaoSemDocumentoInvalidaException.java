package br.com.crux.exception;

import br.com.crux.exception.base.NegocioException;

public class ConciliacaoSemDocumentoInvalidaException extends NegocioException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConciliacaoSemDocumentoInvalidaException(String msg) {
		super(msg);
	}

}
