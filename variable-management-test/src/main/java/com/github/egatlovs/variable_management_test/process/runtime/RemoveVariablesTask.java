package com.github.egatlovs.variable_management_test.process.runtime;

import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.github.egatlovs.variable_management_test.process.variables.MyVariables;
import com.github.egatlovs.variablemanager.managers.RuntimeManager;

@Named("runtimeRemoveVariableTask")
public class RemoveVariablesTask implements JavaDelegate {

	@Inject
	private RuntimeManager manager;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		manager.removeVariables(MyVariables.class, execution.getProcessInstanceId());

		if (manager.getExecutionService().getVariable(execution.getProcessInstanceId(), "stringField") != null) {
			throw new Exception("variable is still present");
		}
	}

}
