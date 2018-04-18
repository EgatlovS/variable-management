package com.github.egatlovs.variablemanager.exceptions;

public class VariableProcessingException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public VariableProcessingException(String message, Exception exception) {
		super(message, exception);
	}

}
