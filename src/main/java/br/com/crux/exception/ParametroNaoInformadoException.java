package br.com.crux.exception;

import br.com.crux.exception.base.NegocioException;

public class ParametroNaoInformadoException extends NegocioException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ParametroNaoInformadoException(String msg) {
		super(msg);
	}

}
