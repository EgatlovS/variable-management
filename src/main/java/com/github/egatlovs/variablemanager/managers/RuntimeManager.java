package com.github.egatlovs.variablemanager.managers;

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
		Map<String, Object> processedVariables = processor.process(value);
		this.runtimeService.setVariables(executionid, processedVariables);
	}

	@Override
	public void setVariableLocal(Object value, String executionid) {
		VariableValidator.validate(value);
		VariableProcessor processor = new VariableProcessor();
		Map<String, Object> processedVariables = processor.process(value);
		this.runtimeService.setVariablesLocal(executionid, processedVariables);
	}

	@Override
	public <T> T getVariable(Class<T> clazz, String executionid) {
		Set<String> variableNames = new FieldNames().get(clazz);
		Map<String, Object> variables = new HashMap<>();
		for (String name : variableNames) {
			variables.put(name, this.runtimeService.getVariable(executionid, name));
		}
		return new ResultObject().getValue(clazz, variables);
	}

	@Override
	public <T> T getVariableLocal(Class<T> clazz, String executionid) {
		Set<String> variableNames = new FieldNames().get(clazz);
		Map<String, Object> variables = new HashMap<>();
		for (String name : variableNames) {
			variables.put(name, this.runtimeService.getVariableLocal(executionid, name));
		}
		return new ResultObject().getValue(clazz, variables);
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
