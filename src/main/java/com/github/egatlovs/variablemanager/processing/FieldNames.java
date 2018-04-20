package com.github.egatlovs.variablemanager.processing;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import com.github.egatlovs.variablemanager.annotations.Execution;
import com.github.egatlovs.variablemanager.annotations.ExecutionField;
import com.github.egatlovs.variablemanager.annotations.Ignore;

public class FieldNames {

	public <T> Set<String> get(Class<T> clazz) {
		Set<String> names;
		ExecutionAnnotation execution = new ExecutionAnnotation(clazz.getAnnotation(Execution.class));
		if (execution.isStoreFields()) {
			names = getNamesFromFields(clazz);
		} else {
			names = getObjectName(clazz);
		}
		return names;
	}

	private <T> Set<String> getObjectName(Class<T> clazz) {
		Set<String> names = new HashSet<>();
		ExecutionField executionField = clazz.getAnnotation(ExecutionField.class);
		if (executionField == null) {
			names.add(clazz.getSimpleName());
		} else if (executionField.prefix() == null || executionField.prefix().isEmpty()) {
			names.add(executionField.name());
		} else {
			names.add(executionField.prefix() + "_" + executionField.name());
		}
		return names;
	}

	private <T> Set<String> getNamesFromFields(Class<T> clazz) {
		Set<String> names = new HashSet<>();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			if (!field.isAnnotationPresent(Ignore.class)) {
				ExecutionFieldAnnotation fieldAnnotation = new ExecutionFieldAnnotation(
						field.getAnnotation(ExecutionField.class), field);
				if (fieldAnnotation.isFieldAnnotationPresent()) {
					names.add(fieldAnnotation.getName());
				} else {
					names.add(field.getName());
				}
			}
		}
		return names;
	}

	@Override
	public String toString() {
		return "FieldNames";
	}

}
