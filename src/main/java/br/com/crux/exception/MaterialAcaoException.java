package br.com.crux.exception;

import br.com.crux.exception.base.NegocioException;

public class MaterialAcaoException extends NegocioException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MaterialAcaoException(String msg) {
		super(msg);
	}

}
