package com.github.egatlovs.variablemanager.managers;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.camunda.bpm.engine.RuntimeService;

import com.github.egatlovs.variablemanager.processing.FieldNames;
import com.github.egatlovs.variablemanager.processing.ResultObject;
import com.github.egatlovs.variablemanager.processing.VariableProcessor;
import com.github.egatlovs.variablemanager.validation.VariableValidator;

@RequestScoped
public class RuntimeManager implements RuntimeVariableManager {

	private RuntimeService runtimeService;

	@Inject
	public RuntimeManager(RuntimeService runtimeService) {
		this.setExecutionService(runtimeService);
	}

	@Override
	public void setVariable(Object value, String executionid) {
		VariableValidator.validate(value);
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
		VariableValidator.validate(value);
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
		try {
			return new ResultObject().getValue(clazz, variables);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO exception handling
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public <T> T getVariableLocal(Class<T> clazz, String executionid) {
		Set<String> variableNames = new FieldNames().get(clazz);
		Map<String, Object> variables = new HashMap<>();
		for (String name : variableNames) {
			variables.put(name, this.runtimeService.getVariableLocal(executionid, name));
		}
		try {
			return new ResultObject().getValue(clazz, variables);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO exception handling
			e.printStackTrace();
			throw new RuntimeException();
		}
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((runtimeService == null) ? 0 : runtimeService.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RuntimeManager other = (RuntimeManager) obj;
		if (runtimeService == null) {
			if (other.runtimeService != null)
				return false;
		} else if (!runtimeService.equals(other.runtimeService))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RuntimeManager [runtimeService=" + runtimeService + "]";
	}

}
