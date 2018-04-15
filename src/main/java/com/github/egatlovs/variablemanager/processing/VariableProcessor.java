package com.github.egatlovs.variablemanager.processing;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;

import com.github.egatlovs.variablemanager.StoreStrategies;
import com.github.egatlovs.variablemanager.annotations.Ignore;

public class VariableProcessor {

	public Map<String, Object> process(Object processable) throws IllegalArgumentException, IllegalAccessException {
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
		ExecutionFieldAnnotation executionField = new ExecutionFieldAnnotation(processable);
		String objectName = executionField.getName();
		if (execution.getStoreStrategy().equals(StoreStrategies.JSON)) {
			processedVariables.put(objectName,
					Variables.objectValue(processable).serializationDataFormat(SerializationDataFormats.JSON));
		} else {
			processedVariables.put(objectName, processable);
		}
		return processedVariables;
	}

	private Map<String, Object> getVariablesFromField(Object processable, ExecutionAnnotation execution)
			throws IllegalArgumentException, IllegalAccessException {
		Map<String, Object> processedVariables = new HashMap<>();
		Field[] fields = processable.getClass().getFields();
		for (Field field : fields) {
			if (!field.isAnnotationPresent(Ignore.class)) {
				ExecutionFieldAnnotation fieldAnnotation = new ExecutionFieldAnnotation(field);
				if (!field.isAccessible()) {
					field.setAccessible(true);
				}
				if (execution.getStoreStrategy().equals(StoreStrategies.JSON)) {
					processedVariables.put(fieldAnnotation.getName(), Variables.objectValue(field.get(processable))
							.serializationDataFormat(SerializationDataFormats.JSON));
				} else {
					processedVariables.put(fieldAnnotation.getName(), field.get(processable));
				}
			}
		}
		return processedVariables;
	}

}
