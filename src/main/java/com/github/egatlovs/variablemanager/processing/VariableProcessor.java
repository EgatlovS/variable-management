package com.github.egatlovs.variablemanager.processing;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;

import com.github.egatlovs.variablemanager.StoreStrategies;
import com.github.egatlovs.variablemanager.annotations.Ignore;
import com.github.egatlovs.variablemanager.exceptions.ExceptionHandler;

public class VariableProcessor {

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
