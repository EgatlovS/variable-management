package com.github.egatlovs.variablemanager.managers;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.camunda.bpm.engine.TaskService;

import com.github.egatlovs.variablemanager.processing.FieldNames;
import com.github.egatlovs.variablemanager.processing.VariableProcessor;

@RequestScoped
public class TaskManager implements TaskVariableManager {

	@Inject
	private TaskService taskService;

	public TaskManager() {
	}

	public TaskManager(TaskService taskService) {
		this.taskService = taskService;
	}

	@Override
	public void setVariable(Object value, String taskid) {
		// TODO Bean Validation first
		VariableProcessor processor = new VariableProcessor();
		try {
			Map<String, Object> processedVariables = processor.process(value);
			this.taskService.setVariables(taskid, processedVariables);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO exception handling
			e.printStackTrace();
		}
	}

	@Override
	public void setVariableLocal(Object value, String taskid) {
		// TODO Bean Validation first
		VariableProcessor processor = new VariableProcessor();
		try {
			Map<String, Object> processedVariables = processor.process(value);
			this.taskService.setVariablesLocal(taskid, processedVariables);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO exception handling
			e.printStackTrace();
		}
	}

	@Override
	public <T> T getVariable(Class<T> clazz, String taskid) {
		Set<String> variableNames = new FieldNames().get(clazz);
		Map<String, Object> variables = new HashMap<>();
		for (String name : variableNames) {
			variables.put(name, this.taskService.getVariable(taskid, name));
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
	public <T> T getVariableLocal(Class<T> clazz, String taskid) {
		Set<String> variableNames = new FieldNames().get(clazz);
		Map<String, Object> variables = new HashMap<>();
		for (String name : variableNames) {
			variables.put(name, this.taskService.getVariableLocal(taskid, name));
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
