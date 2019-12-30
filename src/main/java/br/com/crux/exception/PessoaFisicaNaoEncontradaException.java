package br.com.crux.exception;

import br.com.crux.exception.base.NegocioException;

public class PessoaFisicaNaoEncontradaException extends NegocioException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PessoaFisicaNaoEncontradaException(String msg) {
		super(msg);
	}

}
