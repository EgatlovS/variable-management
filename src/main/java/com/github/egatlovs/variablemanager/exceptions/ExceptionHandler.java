package com.github.egatlovs.variablemanager.exceptions;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * <b>ExceptionHandler</b></br>
 * </br>
 * This class helps creating exceptions inside of the processing classes
 * {@code ResultObject} and {@code VariableProcessor}. Because both of them use
 * Reflection inside many situations where exeptions could be thrown are
 * present. This class uses the thrown exceptions and maps them to more readable
 * messages which help to understand what went wrong.</br>
 * 
 * @author egatlovs
 */
public class ExceptionHandler {

	/**
	 * Maps given Exception to a Result Object Exception.
	 * 
	 * @throws ResultObjectException
	 * @param e
	 *            - thrown Exception
	 * @param clazz
	 *            - Class which caused the exception
	 */
	public static <T> void createResultObjectException(Exception e, Class<T> clazz) {
		if (e instanceof InstantiationException) {
			throw new ResultObjectException(
					"Error received while trying to instantiate object of class " + clazz.getName(), e);
		} else if (e instanceof IllegalAccessException) {
			throw new ResultObjectException(
					"Tryed to instantiate object of class " + clazz.getName() + " but was not accessible", e);
		} else if (e instanceof IllegalArgumentException) {
			throw new ResultObjectException("Tryed to instantiate object of class " + clazz.getName()
					+ " it seems no default constructor is present", e);
		} else if (e instanceof InvocationTargetException) {
			throw new ResultObjectException(
					"Instantiation of object of class " + clazz.getName() + " has thrown an exception ", e);
		} else if (e instanceof NoSuchMethodException) {
			throw new ResultObjectException("Tryed to instantiate object of class " + clazz.getName()
					+ " it seems no default constructor is present", e);
		} else if (e instanceof SecurityException) {
			throw new ResultObjectException(
					"Tryed to instantiate object of class " + clazz.getName() + " but was not accessible", e);
		} else {
			throw new ResultObjectException("Unknown Exception occured", e);
		}
	}

	/**
	 * Maps given Exception to a ResultObjectException.
	 * 
	 * @throws ResultObjectException
	 * @param e
	 *            - thrown Exception
	 * @param field
	 *            - Field which caused the Exception
	 * @param obj
	 *            - Object which caused the Exception
	 */
	public static <T> void createResultObjectException(Exception e, Field field, Object obj) {
		if (e instanceof IllegalAccessException) {
			throw new ResultObjectException("Tryed to access field " + field.getName() + " but was not accessible.", e);
		} else if (e instanceof IllegalArgumentException) {
			throw new ResultObjectException(
					"Tryed to acces field " + field.getName() + " on wrong object " + obj.toString(), e);
		} else {
			throw new ResultObjectException("Unknown Exception occured", e);
		}
	}

	/**
	 * Maps given Exception to a VariableProcessingException.
	 * 
	 * @throws VariableProcessingException
	 * @param e
	 *            - thrown Exception
	 * @param field
	 *            - Field which caused the Exception
	 * @param obj
	 *            - Object which caused the Exception
	 */
	public static void createVariableProcessingException(Exception e, Field field, Object obj) {
		if (e instanceof IllegalAccessException) {
			throw new VariableProcessingException(
					"Tryed to access field " + field.getName() + " but was not accessible.", e);
		} else if (e instanceof IllegalArgumentException) {
			throw new VariableProcessingException(
					"Tryed to acces field " + field.getName() + " on wrong object " + obj.toString(), e);
		} else {
			throw new VariableProcessingException("Unknown Exception occured", e);
		}
	}

}
