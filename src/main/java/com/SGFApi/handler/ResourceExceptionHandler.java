package com.SGFApi.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.SGFApi.domain.DetalhesErro;
import com.SGFApi.services.exception.EstadoNaoEncontradoException;
import com.SGFApi.services.exception.PaisNaoEncontradoException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(PaisNaoEncontradoException.class)
	public ResponseEntity<DetalhesErro> handlePaisNaoEncontradoException(PaisNaoEncontradoException e,
			HttpServletRequest request) {

		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404L);
		erro.setTitulo("O país não pôde ser encontrado");
		erro.setMensagemDesenvolvedor("Link com explicação sobre o erro");
		erro.setTimestamp(System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);

	}

	@ExceptionHandler(EstadoNaoEncontradoException.class)
	public ResponseEntity<DetalhesErro> handleEstadoNaoEncontradoException(EstadoNaoEncontradoException e,
			HttpServletRequest request) {

		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404L);
		erro.setTitulo("O estado não pôde ser encontrado");
		erro.setMensagemDesenvolvedor("Link com explicação sobre o erro");
		erro.setTimestamp(System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);

	}

}
