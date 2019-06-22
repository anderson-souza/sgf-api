package com.SGFApi.services.exception;

public class EstadoNaoEncontradoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 535202430878766106L;

	public EstadoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public EstadoNaoEncontradoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

	public EstadoNaoEncontradoException() {
		super("Estado não pôde ser encontrado");
	}

}
