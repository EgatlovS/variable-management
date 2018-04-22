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

	/**
	 * Sets a Variable to the Execution. </br>
	 * </br>
	 * <b>Note:</b> the variable will be processed as follows:
	 * <ol>
	 * <li>The object will be validated using bean validation.</li>
	 * <li>The given object will be processed using
	 * {@code VariableProcessor.class}</li>
	 * </ol>
	 * If you want more Information of how variables are processed watch out for
	 * {@code VariableProcessor.class}. </br>
	 * If you want to manipulate how your variables are processed look for
	 * {@code @Execution}, {@code @ExecutionField} and {@code @Ignore}. </br>
	 * </br>
	 * 
	 * @param value
	 *            - The value to be set to the Execution
	 */
	@Override
	public void setVariable(Object value) {
		VariableValidator.validate(value);
		VariableProcessor processor = new VariableProcessor();
		Map<String, Object> processedVariables = processor.process(value);
		this.execution.setVariables(processedVariables);
	}

	/**
	 * Sets a Variable Locally to the Execution. </br>
	 * </br>
	 * <b>Note:</b> the variable will be processed as follows:
	 * <ol>
	 * <li>The object will be validated using bean validation.</li>
	 * <li>The given object will be processed using
	 * {@code VariableProcessor.class}</li>
	 * </ol>
	 * If you want more Information of how variables are processed watch out for
	 * {@code VariableProcessor.class}. </br>
	 * If you want to manipulate how your variables are processed look for
	 * {@code @Execution}, {@code @ExecutionField} and {@code @Ignore}. </br>
	 * </br>
	 * 
	 * @param value
	 *            - The value to be set to the Execution
	 */
	@Override
	public void setVariableLocal(Object value) {
		VariableValidator.validate(value);
		VariableProcessor processor = new VariableProcessor();
		Map<String, Object> processedVariables = processor.process(value);
		this.execution.setVariablesLocal(processedVariables);
	}

	/**
	 * Retrieves a Variable from the Execution. </br>
	 * </br>
	 * The variables will be read from the given class using
	 * {@code FieldNames.class}. After that each variable will be called out of the
	 * execution. </br>
	 * Now that each variable is retrieved from the execution, the
	 * {@code ResultObject.class} will be used to build the requested object which
	 * is then returned. </br>
	 * 
	 * @param clazz
	 *            - The Class which should be gathered
	 * @return - The Object of the requested class
	 */
	@Override
	public <T> T getVariable(Class<T> clazz) {
		Set<String> variableNames = new FieldNames().get(clazz);
		Map<String, Object> variables = new HashMap<>();
		for (String name : variableNames) {
			variables.put(name, this.execution.getVariable(name));
		}
		return new ResultObject().getValue(clazz, variables);
	}

	/**
	 * Retrieves a Variable locally from the Execution. </br>
	 * </br>
	 * The variables will be read from the given class using
	 * {@code FieldNames.class}. After that each variable will be called out of the
	 * execution. </br>
	 * Now that each variable is retrieved from the execution, the
	 * {@code ResultObject.class} will be used to build the requested object which
	 * is then returned. </br>
	 * 
	 * @param clazz
	 *            - The Class which should be gathered
	 * @return - The Object of the requested class
	 */
	@Override
	public <T> T getVariableLocal(Class<T> clazz) {
		Set<String> variableNames = new FieldNames().get(clazz);
		Map<String, Object> variables = new HashMap<>();
		for (String name : variableNames) {
			variables.put(name, this.execution.getVariableLocal(name));
		}
		return new ResultObject().getValue(clazz, variables);
	}

	/**
	 * Removes a Variable from the Execution. </br>
	 * </br>
	 * The variables will be read from the given class using
	 * {@code FieldNames.class}. After that each variable will be removed from the
	 * execution. </br>
	 * 
	 * @param clazz
	 *            - The Class which should be used to retrieve the fieldnames to be
	 *            deleted
	 */
	@Override
	public <T> void removeVariables(Class<T> clazz) {
		Set<String> variableNames = new FieldNames().get(clazz);
		this.execution.removeVariables(variableNames);
	}

	/**
	 * Removes Variables locally from the Execution. </br>
	 * </br>
	 * The variables will be read from the given class using
	 * {@code FieldNames.class}. After that each variable will be removed from the
	 * execution. </br>
	 * 
	 * @param clazz
	 *            - The Class which should be used to retrieve the fieldnames to be
	 *            deleted
	 */
	@Override
	public <T> void removeVariablesLocal(Class<T> clazz) {
		Set<String> variableNames = new FieldNames().get(clazz);
		this.execution.removeVariablesLocal(variableNames);
	}

	/**
	 * Gives acces to the wrapped execution.
	 * 
	 * @return Returns the wrapped execution
	 */
	public DelegateExecution getExecutionService() {
		return execution;
	}

	/**
	 * Manipulate the execution used.
	 * 
	 * @param execution
	 *            - The new execution to be used
	 */
	public void setExecutionService(DelegateExecution execution) {
		this.execution = execution;
	}

}
