package br.com.crux.exception;

import br.com.crux.exception.base.NegocioException;

public class UnidadeJaExisteException extends NegocioException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnidadeJaExisteException(String msg) {
		super(msg);
	}

}
