package br.com.crux.exception;

import br.com.crux.exception.base.NegocioException;

public class TokenInvalidoException extends NegocioException{
	
	private static final long serialVersionUID = 1L;

	public TokenInvalidoException(String msg) {
		super(msg);
	}

}
