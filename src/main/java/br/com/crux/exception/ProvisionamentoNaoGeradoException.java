package br.com.crux.exception;

import br.com.crux.exception.base.NegocioException;

public class ProvisionamentoNaoGeradoException extends NegocioException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProvisionamentoNaoGeradoException(String msg) {
		super(msg);
	}

}
