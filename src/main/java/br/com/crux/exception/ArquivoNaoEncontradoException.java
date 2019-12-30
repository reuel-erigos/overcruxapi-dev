package br.com.crux.exception;

import br.com.crux.exception.base.NegocioException;

public class ArquivoNaoEncontradoException extends NegocioException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArquivoNaoEncontradoException(String msg) {
		super(msg);
	}

}
