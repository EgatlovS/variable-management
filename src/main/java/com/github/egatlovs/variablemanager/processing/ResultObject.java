package com.github.egatlovs.variablemanager.processing;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import com.github.egatlovs.variablemanager.annotations.ExecutionField;

public class ResultObject {

	public <T> T getValue(Class<T> clazz, Map<String, Object> variables)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		T obj = clazz.getConstructor().newInstance();

		Field[] fields = clazz.getFields();
		for (Field field : fields) {
			String key;
			if (field.isAnnotationPresent(ExecutionField.class)) {
				ExecutionFieldAnnotation fieldAnnotation = new ExecutionFieldAnnotation(obj);
				key = fieldAnnotation.getName();
			} else {
				key = field.getName();
			}
			field.setAccessible(true);
			field.set(obj, variables.get(key));
		}
		return obj;
	}

	@Override
	public String toString() {
		return "ResultObject";
	}

}
