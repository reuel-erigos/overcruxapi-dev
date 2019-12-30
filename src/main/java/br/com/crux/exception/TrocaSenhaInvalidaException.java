package br.com.crux.exception;

import br.com.crux.exception.base.NegocioException;

public class TrocaSenhaInvalidaException extends NegocioException{
	private static final long serialVersionUID = 1L;

	public TrocaSenhaInvalidaException(String msg) {
		super(msg);
	}

}
