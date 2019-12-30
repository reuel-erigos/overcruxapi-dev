package br.com.crux.exception;

import br.com.crux.exception.base.NegocioException;

public class SemAcessoUnidadeException extends NegocioException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SemAcessoUnidadeException(String msg) {
		super(msg);
	}

}
