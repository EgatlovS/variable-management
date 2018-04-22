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

/**
 * <b>ExecutionManager</b></br>
 * </br>
 * ExecutionManager wrappes a DelegateExecution and offers methods to set, get
 * and remove variables based of an ExecutionEntity.</br>
 * The Manager it self uses BeanValidation on each given Object, so you can
 * simply annotate your ExecutionEntitys with the well known BeanValidation
 * annotations. </br>
 * </br>
 * The ExecutionManager is injectable. If you inject it in your bean it will
 * initialize itself with the current delegate execution if present. Otherwise
 * if no execution is present the Manager will initialize itself with null.
 * 
 * @author egatlovs
 */
@RequestScoped
public class ExecutionManager implements ExecutionVariableManager {

	private DelegateExecution execution;

	/**
	 * Constructor defining the execution to be used.
	 * 
	 * @param execution
	 *            - The execution to be used or the CurrentExcution if injected
	 */
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

	@Override
	public DelegateExecution getExecutionService() {
		return execution;
	}

	@Override
	public void setExecutionService(DelegateExecution execution) {
		this.execution = execution;
	}

}
