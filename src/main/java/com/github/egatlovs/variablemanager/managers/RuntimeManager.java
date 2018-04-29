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

/**
 * <b>RuntimeManager</b></br>
 * </br>
 * RuntimeManager wraps a RuntimeService and offers methods to set, get and
 * remove variables based of an ExecutionEntity.</br>
 * The Manager itself uses BeanValidation on each given Object, so you can
 * simply annotate your ExecutionEntitys with the well known BeanValidation
 * annotations. </br>
 * </br>
 * The RuntimeManager is injectable. If you inject it in your bean it will
 * initialize itself with the RuntimeManager provided in the environment.
 * 
 * @author egatlovs
 */
@RequestScoped
public class RuntimeManager implements RuntimeVariableManager {

	@Inject
	private RuntimeService runtimeService;

	/**
	 * Constructor defining the runtimeService to be used.
	 * 
	 * @param runtimeService
	 *            - The runtimeService to be used
	 */
	public RuntimeManager(RuntimeService runtimeService) {
		this.setExecutionService(runtimeService);
	}

	public RuntimeManager() {
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

	@Override
	public RuntimeService getExecutionService() {
		return runtimeService;
	}

	@Override
	public void setExecutionService(RuntimeService runtimeService) {
		this.runtimeService = runtimeService;
	}

}
