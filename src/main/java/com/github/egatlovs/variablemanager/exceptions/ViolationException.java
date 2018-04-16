package com.github.egatlovs.variablemanager.exceptions;

import com.github.egatlovs.variablemanager.validation.ConstraintViolationMessage;

public class ViolationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ViolationException(ConstraintViolationMessage violation) {
		super(violation.getMessage());
	}

}
