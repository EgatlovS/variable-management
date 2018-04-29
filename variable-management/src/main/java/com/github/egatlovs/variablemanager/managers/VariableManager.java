package com.github.egatlovs.variablemanager.managers;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class VariableManager {

	@Inject
	private ExecutionVariableManager executionManager;
	@Inject
	private RuntimeVariableManager runtimeManager;
	@Inject
	private TaskVariableManager taskManager;

	public VariableManager(ExecutionVariableManager executionManager, RuntimeVariableManager runtimeManager,
			TaskVariableManager taskManager) {
		this.executionManager = executionManager;
		this.runtimeManager = runtimeManager;
		this.taskManager = taskManager;
	}

	@Inject
	public VariableManager() {
	}

	public ExecutionVariableManager getExecutionManager() {
		return executionManager;
	}

	public void setExecutionManager(ExecutionVariableManager executionManager) {
		this.executionManager = executionManager;
	}

	public RuntimeVariableManager getRuntimeManager() {
		return runtimeManager;
	}

	public void setRuntimeManager(RuntimeVariableManager runtimeManager) {
		this.runtimeManager = runtimeManager;
	}

	public TaskVariableManager getTaskManager() {
		return taskManager;
	}

	public void setTaskManager(TaskVariableManager taskManager) {
		this.taskManager = taskManager;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((executionManager == null) ? 0 : executionManager.hashCode());
		result = prime * result + ((runtimeManager == null) ? 0 : runtimeManager.hashCode());
		result = prime * result + ((taskManager == null) ? 0 : taskManager.hashCode());
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
		VariableManager other = (VariableManager) obj;
		if (executionManager == null) {
			if (other.executionManager != null)
				return false;
		} else if (!executionManager.equals(other.executionManager))
			return false;
		if (runtimeManager == null) {
			if (other.runtimeManager != null)
				return false;
		} else if (!runtimeManager.equals(other.runtimeManager))
			return false;
		if (taskManager == null) {
			if (other.taskManager != null)
				return false;
		} else if (!taskManager.equals(other.taskManager))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "VariableManager [executionManager=" + executionManager + ", runtimeManager=" + runtimeManager
				+ ", taskManager=" + taskManager + "]";
	}

}
