package br.com.crux.exception;

import br.com.crux.exception.base.NegocioException;

public class ModuloNaoEncontradoException extends NegocioException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ModuloNaoEncontradoException(String msg) {
		super(msg);
	}

}
