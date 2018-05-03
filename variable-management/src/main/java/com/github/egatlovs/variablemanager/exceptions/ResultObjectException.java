package com.github.egatlovs.variablemanager.exceptions;

/**
 * <b>ResultObjectException</b></br>
 * </br>
 * This is a RuntimeException which will be thrown if the processing of a result
 * object fails.
 * 
 * @author egatlovs
 */
public class ResultObjectException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResultObjectException(String message, Exception exception) {
		super(message, exception);
	}

	public ResultObjectException(String message) {
		super(message);
	}

}
