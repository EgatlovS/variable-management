package com.github.egatlovs.variablemanager.exceptions;

import com.github.egatlovs.variablemanager.validation.ConstraintViolationMessage;

/**
 * <b>ViolationException</b></br>
 * </br>
 * This is a RuntimeException which will be thrown if the validation of an
 * ExecutionEntity fails. The message contains each validation failure.
 * 
 * @author egatlovs
 */
public class ViolationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ViolationException(ConstraintViolationMessage violation) {
		super(violation.getMessage());
	}

}
