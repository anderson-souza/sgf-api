package com.SGFApi.services.exception;

public class PaisNaoEncontradoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2319142823515362500L;

	public PaisNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public PaisNaoEncontradoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

	public PaisNaoEncontradoException() {
		super("Pais informado não pôde ser encontrado");
	}

}
