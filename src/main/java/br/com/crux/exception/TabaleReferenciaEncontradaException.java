package br.com.crux.exception;

import br.com.crux.exception.base.NegocioException;

public class TabaleReferenciaEncontradaException extends NegocioException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TabaleReferenciaEncontradaException(String msg) {
		super(msg);
	}

}
