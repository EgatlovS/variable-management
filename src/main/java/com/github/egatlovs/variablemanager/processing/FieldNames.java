package com.github.egatlovs.variablemanager.processing;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import com.github.egatlovs.variablemanager.annotations.Execution;
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
		FieldName fieldName = new FieldName();
		names.add(fieldName.getFrom(clazz));
		return names;
	}

	private <T> Set<String> getNamesFromFields(Class<T> clazz) {
		Set<String> names = new HashSet<>();
		Field[] fields = clazz.getDeclaredFields();
		FieldName fieldName = new FieldName();
		for (Field field : fields) {
			if (!field.isSynthetic() && !field.isAnnotationPresent(Ignore.class)) {
				names.add(fieldName.getFrom(field));
			}
		}
		return names;
	}

}
