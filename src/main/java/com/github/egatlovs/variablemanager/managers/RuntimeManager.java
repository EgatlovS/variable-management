package com.github.egatlovs.variablemanager.managers;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.camunda.bpm.engine.RuntimeService;

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
		// TODO Auto-generated method stub

	}

	@Override
	public void setVariableLocal(Object value, String executionid) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> T getVariable(Class<T> clazz, String executionid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T getVariableLocal(Class<T> clazz, String executionid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeVariables(Object value, String executionid) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeVariablesLocal(Object value, String executionid) {
		// TODO Auto-generated method stub

	}

	public RuntimeService getExecutionService() {
		return runtimeService;
	}

	public void setExecutionService(RuntimeService runtimeService) {
		this.runtimeService = runtimeService;
	}

}
