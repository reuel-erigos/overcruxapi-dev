package br.com.crux.exception;

import br.com.crux.exception.base.NegocioException;

public class PerfilAcessoException extends NegocioException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PerfilAcessoException(String msg) {
		super(msg);
	}

}
