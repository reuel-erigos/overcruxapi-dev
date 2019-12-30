package br.com.crux.exception;

import br.com.crux.exception.base.NegocioException;

public class UsuarioSemAcessoException extends NegocioException{
	
	private static final long serialVersionUID = 1L;

	public UsuarioSemAcessoException(String msg) {
		super(msg);
	}

}
