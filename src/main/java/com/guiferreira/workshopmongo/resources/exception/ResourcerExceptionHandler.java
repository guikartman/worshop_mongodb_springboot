package com.guiferreira.workshopmongo.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.guiferreira.workshopmongo.services.exception.ObjectNotFoundException;

/**
 * Classe responsavél por tratar possivéis erros nas minhas requisições
 * 
 * @author guilherme
 *
 */
@ControllerAdvice
public class ResourcerExceptionHandler {
	
	/**
	 * Trata a excessão de ObjectNotFoundException
	 * 
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> ObjectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Não encontrado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
