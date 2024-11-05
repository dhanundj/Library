package com.avr.library.exception;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GbloballibraryException {

	@ExceptionHandler(value = LibraryException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ProblemDetail libraryExist(LibraryException e) {
		ProblemDetail p = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
		p.setProperty("Host", "localHost");
		p.setProperty("Post", "8080");
		return p;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

		Map<String, String> errors = new HashMap<>();

		List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();

		allErrors.forEach(err -> {

			FieldError fe = (FieldError) err;

			errors.put(fe.getField(), fe.getDefaultMessage());

		});

		return errors;

	}

	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ProblemDetail handleAccessDeniedException(AccessDeniedException e) {
		ProblemDetail p = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, "Access Denied: " + e.getMessage());
		p.setProperty("Host", "localHost");
		p.setProperty("Post", "8080");
		return p;
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<String> handleUsernameNotFoundException(UsernameNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}

}