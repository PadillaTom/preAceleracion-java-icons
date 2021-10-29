package com.padillatomas.icons.icons.controller;

import java.util.Arrays;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.padillatomas.icons.icons.dto.ApiErrorDTO;
import com.padillatomas.icons.icons.exception.ParamNotFound;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	// == PARAM NOT FOUND ==
	
	// Definimos un Handler
	@ExceptionHandler(value = {ParamNotFound.class})
	protected ResponseEntity<Object> handleParamNotFound(RuntimeException ex, WebRequest request){
		// Creamos un DTO que contendra nuestra data:
		ApiErrorDTO errorDTO = new ApiErrorDTO(
					HttpStatus.BAD_REQUEST,
					ex.getMessage(),
					Arrays.asList("Exception: Param Not Found.")
				);
		// Devolvemos el HANDLE junto con nuestro DTO (TODO SERA ENVIADO)
		return handleExceptionInternal(ex, errorDTO, new HttpHeaders(),HttpStatus.BAD_REQUEST,  request  );
	}
	
	// == VALID ==
	
}
