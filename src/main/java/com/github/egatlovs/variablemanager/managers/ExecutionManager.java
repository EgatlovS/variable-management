package com.github.egatlovs.variablemanager.managers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.camunda.bpm.engine.delegate.DelegateExecution;

import com.github.egatlovs.variablemanager.processing.FieldNames;
import com.github.egatlovs.variablemanager.processing.ResultObject;
import com.github.egatlovs.variablemanager.processing.VariableProcessor;
import com.github.egatlovs.variablemanager.validation.VariableValidator;

@RequestScoped
public class ExecutionManager implements ExecutionVariableManager {

	private DelegateExecution execution;

	@Inject
	public ExecutionManager(DelegateExecution execution) {
		this.execution = execution;
	}

	@Override
	public void setVariable(Object value) {
		VariableValidator.validate(value);
		VariableProcessor processor = new VariableProcessor();
		Map<String, Object> processedVariables = processor.process(value);
		this.execution.setVariables(processedVariables);
	}

	@Override
	public void setVariableLocal(Object value) {
		VariableValidator.validate(value);
		VariableProcessor processor = new VariableProcessor();
		Map<String, Object> processedVariables = processor.process(value);
		this.execution.setVariablesLocal(processedVariables);
	}

	@Override
	public <T> T getVariable(Class<T> clazz) {
		Set<String> variableNames = new FieldNames().get(clazz);
		Map<String, Object> variables = new HashMap<>();
		for (String name : variableNames) {
			variables.put(name, this.execution.getVariable(name));
		}
		return new ResultObject().getValue(clazz, variables);
	}

	@Override
	public <T> T getVariableLocal(Class<T> clazz) {
		Set<String> variableNames = new FieldNames().get(clazz);
		Map<String, Object> variables = new HashMap<>();
		for (String name : variableNames) {
			variables.put(name, this.execution.getVariableLocal(name));
		}
		return new ResultObject().getValue(clazz, variables);
	}

	@Override
	public <T> void removeVariables(Class<T> clazz) {
		Set<String> variableNames = new FieldNames().get(clazz);
		this.execution.removeVariables(variableNames);
	}

	@Override
	public <T> void removeVariablesLocal(Class<T> clazz) {
		Set<String> variableNames = new FieldNames().get(clazz);
		this.execution.removeVariablesLocal(variableNames);
	}

	public DelegateExecution getExecutionService() {
		return execution;
	}

	public void setExecutionService(DelegateExecution execution) {
		this.execution = execution;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((execution == null) ? 0 : execution.hashCode());
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
		ExecutionManager other = (ExecutionManager) obj;
		if (execution == null) {
			if (other.execution != null)
				return false;
		} else if (!execution.equals(other.execution))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ExecutionManager [execution=" + execution + "]";
	}
}
