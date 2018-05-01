package com.github.egatlovs.variablemanager.processing;

import java.lang.reflect.Field;

import com.github.egatlovs.variablemanager.annotations.FieldName;

/**
 * <b>FieldName</b></br>
 * </br>
 * FieldName is capable of building a name of a given Field, Class or Object.
 * 
 * @author egatlovs
 */
public class FieldNameExtractor {

	/**
	 * Returns the name of the given Field. If the Field is annotated with
	 * com.github.egatlovs.variablemanager.annotations.FieldName the values will be recognized and used to provide the right
	 * name.
	 * 
	 * @param field
	 *            - The field to retrieve the name from
	 * @return The name of the given field
	 */
	public String getFrom(Field field) {
		com.github.egatlovs.variablemanager.annotations.FieldName executionField = field.getAnnotation(com.github.egatlovs.variablemanager.annotations.FieldName.class);
		String name;
		if (executionField == null) {
			name = field.getName();
		} else {
			executionField.prefix();
			if (executionField.prefix().isEmpty()) {
				name = executionField.name();
			} else {
				name = executionField.prefix() + "_" + executionField.name();
			}
		}
		return name;
	}

	/**
	 * Returns the name of the given Class. If the Class is annotated with
	 * com.github.egatlovs.variablemanager.annotations.FieldName the values will be recognized and used to provide the right
	 * name.
	 * 
	 * @param clazz
	 *            - The Class to retrieve the name from
	 * @return The name of the given Class
	 */
	public String getFrom(@SuppressWarnings("rawtypes") Class clazz) {
		@SuppressWarnings("unchecked")
		com.github.egatlovs.variablemanager.annotations.FieldName executionField = (com.github.egatlovs.variablemanager.annotations.FieldName) clazz.getAnnotation(com.github.egatlovs.variablemanager.annotations.FieldName.class);
		String name;
		if (executionField == null) {
			name = clazz.getSimpleName();
		} else {
			executionField.prefix();
			if (executionField.prefix().isEmpty()) {
				name = executionField.name();
			} else {
				name = executionField.prefix() + "_" + executionField.name();
			}
		}
		return name;
	}

	/**
	 * Returns the name of the given Object. If the Object is annotated with
	 * com.github.egatlovs.variablemanager.annotations.FieldName the values will be recognized and used to provide the right
	 * name.
	 * 
	 * @param o
	 *            - The Object to retrieve the name from
	 * @return The name of the given Object
	 */
	public String getFrom(Object o) {
		com.github.egatlovs.variablemanager.annotations.FieldName executionField = o.getClass().getAnnotation(com.github.egatlovs.variablemanager.annotations.FieldName.class);
		String name;
		if (executionField == null) {
			name = o.getClass().getSimpleName();
		} else {
			executionField.prefix();
			if (executionField.prefix().isEmpty()) {
				name = executionField.name();
			} else {
				name = executionField.prefix() + "_" + executionField.name();
			}
		}
		return name;
	}

}
