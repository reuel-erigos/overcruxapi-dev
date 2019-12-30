package br.com.crux.exception;

import br.com.crux.exception.base.NegocioException;

public class NotFoundException extends NegocioException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotFoundException(String msg) {
		super(msg);
	}

}
