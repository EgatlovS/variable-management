package com.github.egatlovs.variablemanager.processing;

import java.lang.reflect.Field;
import java.util.Map;

import com.github.egatlovs.variablemanager.annotations.ExecutionField;
import com.github.egatlovs.variablemanager.exceptions.ExceptionHandler;

public class ResultObject {

	public <T> T getValue(Class<T> clazz, Map<String, Object> variables) {
		T obj = null;
		try {
			obj = clazz.getConstructor().newInstance();
		} catch (Exception e) {
			ExceptionHandler.createResultObjectException(e, clazz);
		}

		Field[] fields = clazz.getDeclaredFields();
		FieldName fieldName = new FieldName();
		for (Field field : fields) {
			String key;
			if (field.isAnnotationPresent(ExecutionField.class)) {
				key = fieldName.getFrom(field);
			} else {
				key = field.getName();
			}
			field.setAccessible(true);
			try {
				field.set(obj, variables.get(key));
			} catch (Exception e) {
				ExceptionHandler.createResultObjectException(e, field, obj);
			}
		}
		return obj;
	}

}
