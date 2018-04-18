package com.github.egatlovs.variablemanager.exceptions;

public class ResultObjectException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResultObjectException(String message, Exception exception) {
		super(message, exception);
	}

}
