package br.com.crux.exception;

import br.com.crux.exception.base.NegocioException;

public class UsuarioSistemaNaoEncontradoException extends NegocioException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioSistemaNaoEncontradoException(String msg) {
		super(msg);
	}

}
