package com.github.egatlovs.variablemanager.processing;

import java.lang.reflect.Field;
import java.util.Map;

import com.github.egatlovs.variablemanager.annotations.FieldName;
import com.github.egatlovs.variablemanager.exceptions.ExceptionHandler;
import com.github.egatlovs.variablemanager.exceptions.ResultObjectException;

/**
 * <b>ResultObject</b></br>
 * </br>
 * ResultObject represents an ExecutionEntity.</br>
 * After the variables of a given Class are gathered from the Execution, a
 * ResultObject can be created. It takes those variables and a Class which it
 * should represent. This class then instantiates the given Class and maps the
 * variables to it. </br>
 * 
 * @author egatlovs
 */
@SuppressWarnings("JavaDoc")
public class ResultObject {

	/**
	 * GetValue creates an Object of the given Class and maps all the given
	 * variables to the corresponding Fields.
	 * 
	 * @throws ResultObjectException
	 * @param clazz
	 *            - The class to be created
	 * @param variables
	 *            - The variables to be mapped
	 * @return The created Object
	 */
	public <T> T getValue(Class<T> clazz, Map<String, Object> variables) {
		T obj = null;
		try {
			obj = clazz.getConstructor().newInstance();
		} catch (Exception e) {
			ExceptionHandler.createResultObjectException(e, clazz);
		}

		Field[] fields = clazz.getDeclaredFields();
		com.github.egatlovs.variablemanager.processing.FieldNameExtractor fieldName = new com.github.egatlovs.variablemanager.processing.FieldNameExtractor();
		for (Field field : fields) {
			String key;
			if (field.isAnnotationPresent(FieldName.class)) {
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
