package com.github.egatlovs.variablemanager.processing;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;

import com.github.egatlovs.variablemanager.annotations.Ignore;
import com.github.egatlovs.variablemanager.annotations.StoreStrategies;
import com.github.egatlovs.variablemanager.exceptions.ExceptionHandler;
import com.github.egatlovs.variablemanager.exceptions.VariableProcessingException;

/**
 * <b>VariableProcessor</b></br>
 * </br>
 * This class processes a given Object and extracts each variable declared in
 * it.</br>
 * After processing, a Map of variables and their execution names is
 * returned.</br>
 * </br>
 * <b>Note:</b></br>
 * <i>You can manipulate the outcome of the Class by using following
 * annotations:</i>
 * <ul>
 * <li>{@code @Ignore}</li>
 * <li>{@code @Execution}</li>
 * <li>{@code @ExecutionAnnotation}</li>
 * </ul>
 * </br>
 * 
 * @author egatlovs
 */
public class VariableProcessor {

	/**
	 * Process takes an Object and extracts each variable declared in it.</br>
	 * After extracting the variables and their names a Map of them is
	 * returned.</br>
	 * </br>
	 * <b>Note:</b></br>
	 * <i>You can manipulate the outcome of the Class by using following
	 * annotations:</i>
	 * <ul>
	 * <li>{@code @Ignore}</li>
	 * <li>{@code @Execution}</li>
	 * <li>{@code @ExecutionAnnotation}</li>
	 * </ul>
	 * </br>
	 * 
	 * @throws VariableProcessingException
	 * @param processable
	 *            - The Object being processed
	 * @return A Map of variables and their names
	 */
	@SuppressWarnings("JavaDoc")
	public Map<String, Object> process(Object processable) {
		Map<String, Object> processedVariables;
		ExecutionAnnotation execution = new ExecutionAnnotation(processable);
		if (execution.isStoreFields()) {
			processedVariables = getVariablesFromField(processable, execution);
		} else {
			processedVariables = getObjectVariable(processable, execution);
		}
		return processedVariables;
	}

	private Map<String, Object> getObjectVariable(Object processable, ExecutionAnnotation execution) {
		Map<String, Object> processedVariables = new HashMap<>();
		FieldName fieldName = new FieldName();
		String objectName = fieldName.getFrom(processable);
		if (execution.getStoreStrategy().equals(StoreStrategies.JSON)) {
			processedVariables.put(objectName,
					Variables.objectValue(processable).serializationDataFormat(SerializationDataFormats.JSON).create());
		} else {
			processedVariables.put(objectName, processable);
		}
		return processedVariables;
	}

	private Map<String, Object> getVariablesFromField(Object processable, ExecutionAnnotation execution) {
		Map<String, Object> processedVariables = new HashMap<>();
		Field[] fields = processable.getClass().getDeclaredFields();
		FieldName fieldName = new FieldName();
		for (Field field : fields) {
			if (!field.isSynthetic() && !field.isAnnotationPresent(Ignore.class)) {
				if (!field.isAccessible()) {
					field.setAccessible(true);
				}
				try {
					if (execution.getStoreStrategy().equals(StoreStrategies.JSON)) {

						processedVariables.put(fieldName.getFrom(field), Variables.objectValue(field.get(processable))
								.serializationDataFormat(SerializationDataFormats.JSON).create());

					} else {
						processedVariables.put(fieldName.getFrom(field), field.get(processable));
					}
				} catch (Exception e) {
					ExceptionHandler.createVariableProcessingException(e, field, processable);
				}
			}
		}
		return processedVariables;
	}

}
