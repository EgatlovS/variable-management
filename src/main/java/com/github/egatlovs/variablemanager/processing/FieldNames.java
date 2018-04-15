package com.github.egatlovs.variablemanager.processing;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import com.github.egatlovs.variablemanager.annotations.Ignore;

public class FieldNames {

	public <T> Set<String> get(Class<T> clazz) {
		Set<String> names;
		ExecutionAnnotation execution = new ExecutionAnnotation(clazz);
		if (execution.isStoreFields()) {
			names = getNamesFromFields(clazz);
		} else {
			names = getObjectName(clazz);
		}
		return names;
	}

	private <T> Set<String> getObjectName(Class<T> clazz) {
		Set<String> names = new HashSet<>();
		ExecutionFieldAnnotation executionField = new ExecutionFieldAnnotation(clazz);
		names.add(executionField.getName());
		return names;
	}

	private <T> Set<String> getNamesFromFields(Class<T> clazz) {
		Set<String> names = new HashSet<>();
		Field[] fields = clazz.getFields();
		for (Field field : fields) {
			if (!field.isAnnotationPresent(Ignore.class)) {
				ExecutionFieldAnnotation fieldAnnotation = new ExecutionFieldAnnotation(field);
				names.add(fieldAnnotation.getName());
			}
		}
		return names;
	}

}
