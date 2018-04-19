package com.github.egatlovs.variablemanager.exceptions;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class ExceptionHandler {

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
