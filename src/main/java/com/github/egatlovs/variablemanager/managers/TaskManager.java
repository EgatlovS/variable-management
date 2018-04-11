package com.github.egatlovs.variablemanager.managers;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.camunda.bpm.engine.TaskService;

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
		// TODO Auto-generated method stub

	}

	@Override
	public void setVariableLocal(Object value, String taskid) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> T getVariable(Class<T> clazz, String taskid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T getVariableLocal(Class<T> clazz, String taskid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeVariables(Object value, String taskid) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeVariablesLocal(Object value, String taskid) {
		// TODO Auto-generated method stub

	}

	public TaskService getExecutionService() {
		return taskService;
	}

	public void setExecutionService(TaskService taskService) {
		this.taskService = taskService;
	}

}
