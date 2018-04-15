package com.github.egatlovs.variablemanager.managers;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.camunda.bpm.engine.RuntimeService;

import com.github.egatlovs.variablemanager.processing.FieldNames;
import com.github.egatlovs.variablemanager.processing.VariableProcessor;

@RequestScoped
public class RuntimeManager implements RuntimeVariableManager {

	@Inject
	private RuntimeService runtimeService;

	public RuntimeManager() {
	}

	public RuntimeManager(RuntimeService runtimeService) {
		this.setExecutionService(runtimeService);
	}

	@Override
	public void setVariable(Object value, String executionid) {
		// TODO Bean Validation first
		VariableProcessor processor = new VariableProcessor();
		try {
			Map<String, Object> processedVariables = processor.process(value);
			this.runtimeService.setVariables(executionid, processedVariables);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO exception handling
			e.printStackTrace();
		}
	}

	@Override
	public void setVariableLocal(Object value, String executionid) {
		// TODO Bean Validation first
		VariableProcessor processor = new VariableProcessor();
		try {
			Map<String, Object> processedVariables = processor.process(value);
			this.runtimeService.setVariablesLocal(executionid, processedVariables);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO exception handling
			e.printStackTrace();
		}
	}

	@Override
	public <T> T getVariable(Class<T> clazz, String executionid) {
		Set<String> variableNames = new FieldNames().get(clazz);
		Map<String, Object> variables = new HashMap<>();
		for (String name : variableNames) {
			variables.put(name, this.runtimeService.getVariable(executionid, name));
		}
		T obj = null;
		try {
			obj = clazz.getConstructor().newInstance();
			// TODO set variables to obj
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			// TODO exception handling
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public <T> T getVariableLocal(Class<T> clazz, String executionid) {
		Set<String> variableNames = new FieldNames().get(clazz);
		Map<String, Object> variables = new HashMap<>();
		for (String name : variableNames) {
			variables.put(name, this.runtimeService.getVariableLocal(executionid, name));
		}
		T obj = null;
		try {
			obj = clazz.getConstructor().newInstance();
			// TODO set variables to obj
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			// TODO exception handling
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public <T> void removeVariables(Class<T> clazz, String executionid) {
		Set<String> variableNames = new FieldNames().get(clazz);
		this.runtimeService.removeVariables(executionid, variableNames);
	}

	@Override
	public <T> void removeVariablesLocal(Class<T> clazz, String executionid) {
		Set<String> variableNames = new FieldNames().get(clazz);
		this.runtimeService.removeVariablesLocal(executionid, variableNames);
	}

	public RuntimeService getExecutionService() {
		return runtimeService;
	}

	public void setExecutionService(RuntimeService runtimeService) {
		this.runtimeService = runtimeService;
	}

}
