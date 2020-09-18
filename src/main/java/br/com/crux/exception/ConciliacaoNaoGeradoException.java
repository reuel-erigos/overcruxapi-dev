package br.com.crux.exception;

import br.com.crux.exception.base.NegocioException;

public class ConciliacaoNaoGeradoException extends NegocioException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConciliacaoNaoGeradoException(String msg) {
		super(msg);
	}

}
