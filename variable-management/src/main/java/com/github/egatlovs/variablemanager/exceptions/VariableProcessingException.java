package com.github.egatlovs.variablemanager.exceptions;

/**
 * <b>VariableProcessingException</b></br>
 * </br>
 * This is a RuntimeException which will be thrown if the processing of
 * variables fail.
 * 
 * @author egatlovs
 */
public class VariableProcessingException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public VariableProcessingException(String message, Exception exception) {
		super(message, exception);
	}

}
