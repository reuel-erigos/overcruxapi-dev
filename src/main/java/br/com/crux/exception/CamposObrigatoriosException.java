package br.com.crux.exception;

import br.com.crux.exception.base.NegocioException;

public class CamposObrigatoriosException extends NegocioException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CamposObrigatoriosException(String msg) {
		super(msg);
	}

}
