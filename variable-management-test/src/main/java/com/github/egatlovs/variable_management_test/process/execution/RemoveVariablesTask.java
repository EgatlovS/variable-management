package com.github.egatlovs.variable_management_test.process.execution;

import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.github.egatlovs.variable_management_test.process.variables.MyVariables;
import com.github.egatlovs.variablemanager.managers.ExecutionManager;

@Named("executionRemoveVariableTask")
public class RemoveVariablesTask implements JavaDelegate {

	@Inject
	private ExecutionManager manager;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		manager.removeVariables(MyVariables.class);

		if (manager.getExecutionService().getVariable("stringField") != null) {
			throw new Exception("variable is still present");
		}
	}

}
