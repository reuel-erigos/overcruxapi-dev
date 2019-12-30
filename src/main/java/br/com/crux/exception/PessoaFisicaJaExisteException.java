package br.com.crux.exception;

import br.com.crux.exception.base.NegocioException;

public class PessoaFisicaJaExisteException extends NegocioException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PessoaFisicaJaExisteException(String msg) {
		super(msg);
	}

}
