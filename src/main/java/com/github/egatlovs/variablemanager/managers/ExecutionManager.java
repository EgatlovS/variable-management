package com.github.egatlovs.variablemanager.managers;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;

import org.camunda.bpm.engine.context.DelegateExecutionContext;
import org.camunda.bpm.engine.delegate.DelegateExecution;

@RequestScoped
public class ExecutionManager implements ExecutionVariableManager {

	private DelegateExecution execution;

	@PostConstruct
	public void init() {
		this.execution = DelegateExecutionContext.getCurrentDelegationExecution();
	}

	public ExecutionManager() {
	}

	public ExecutionManager(DelegateExecution execution) {
		this.execution = execution;
	}

	@Override
	public void setVariable(Object value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setVariableLocal(Object value) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> T getVariable(Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T getVariableLocal(Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeVariables(Object value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeVariablesLocal(Object value) {
		// TODO Auto-generated method stub

	}

	public DelegateExecution getExecutionService() {
		return execution;
	}

	public void setExecutionService(DelegateExecution execution) {
		this.execution = execution;
	}
}
