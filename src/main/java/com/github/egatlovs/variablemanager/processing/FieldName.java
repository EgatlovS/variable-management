package com.github.egatlovs.variablemanager.processing;

import java.lang.reflect.Field;

import com.github.egatlovs.variablemanager.annotations.ExecutionField;

public class FieldName {

	public String getFrom(Field field) {
		ExecutionField executionField = field.getAnnotation(ExecutionField.class);
		String name;
		if (executionField == null) {
			name = field.getName();
		} else if (executionField.prefix() == null || executionField.prefix().isEmpty()) {
			name = executionField.name();
		} else {
			name = executionField.prefix() + "_" + executionField.name();
		}
		return name;
	}

	public String getFrom(@SuppressWarnings("rawtypes") Class clazz) {
		@SuppressWarnings("unchecked")
		ExecutionField executionField = (ExecutionField) clazz.getAnnotation(ExecutionField.class);
		String name;
		if (executionField == null) {
			name = clazz.getSimpleName();
		} else if (executionField.prefix() == null || executionField.prefix().isEmpty()) {
			name = executionField.name();
		} else {
			name = executionField.prefix() + "_" + executionField.name();
		}
		return name;
	}

	public String getFrom(Object o) {
		ExecutionField executionField = o.getClass().getAnnotation(ExecutionField.class);
		String name;
		if (executionField == null) {
			name = o.getClass().getSimpleName();
		} else if (executionField.prefix() == null || executionField.prefix().isEmpty()) {
			name = executionField.name();
		} else {
			name = executionField.prefix() + "_" + executionField.name();
		}
		return name;
	}

}
