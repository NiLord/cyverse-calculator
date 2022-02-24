package com.nilord.cyverse.calculator.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Main exception class for services
 * 
 * @author nilord
 *
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Unspected error")
public class CyverseCalculationException extends RuntimeException {

	private static final long serialVersionUID = 2674880805289764721L;

	public CyverseCalculationException(String errorMessage) {
		super(errorMessage);
	}

}
