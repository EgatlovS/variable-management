package com.github.egatlovs.variablemanager.managers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.camunda.bpm.engine.TaskService;

import com.github.egatlovs.variablemanager.processing.FieldNames;
import com.github.egatlovs.variablemanager.processing.ResultObject;
import com.github.egatlovs.variablemanager.processing.VariableProcessor;
import com.github.egatlovs.variablemanager.validation.VariableValidator;

@RequestScoped
public class TaskManager implements TaskVariableManager {

	private TaskService taskService;

	@Inject
	public TaskManager(TaskService taskService) {
		this.taskService = taskService;
	}

	@Override
	public void setVariable(Object value, String taskid) {
		VariableValidator.validate(value);
		VariableProcessor processor = new VariableProcessor();
		Map<String, Object> processedVariables = processor.process(value);
		this.taskService.setVariables(taskid, processedVariables);
	}

	@Override
	public void setVariableLocal(Object value, String taskid) {
		VariableValidator.validate(value);
		VariableProcessor processor = new VariableProcessor();
		Map<String, Object> processedVariables = processor.process(value);
		this.taskService.setVariablesLocal(taskid, processedVariables);
	}

	@Override
	public <T> T getVariable(Class<T> clazz, String taskid) {
		Set<String> variableNames = new FieldNames().get(clazz);
		Map<String, Object> variables = new HashMap<>();
		for (String name : variableNames) {
			variables.put(name, this.taskService.getVariable(taskid, name));
		}
		return new ResultObject().getValue(clazz, variables);
	}

	@Override
	public <T> T getVariableLocal(Class<T> clazz, String taskid) {
		Set<String> variableNames = new FieldNames().get(clazz);
		Map<String, Object> variables = new HashMap<>();
		for (String name : variableNames) {
			variables.put(name, this.taskService.getVariableLocal(taskid, name));
		}
		return new ResultObject().getValue(clazz, variables);
	}

	@Override
	public <T> void removeVariables(Class<T> clazz, String taskid) {
		Set<String> variableNames = new FieldNames().get(clazz);
		this.taskService.removeVariables(taskid, variableNames);
	}

	@Override
	public <T> void removeVariablesLocal(Class<T> clazz, String taskid) {
		Set<String> variableNames = new FieldNames().get(clazz);
		this.taskService.removeVariablesLocal(taskid, variableNames);
	}

	public TaskService getExecutionService() {
		return taskService;
	}

	public void setExecutionService(TaskService taskService) {
		this.taskService = taskService;
	}

}
