package com.github.egatlovs.variable_management_test.process.execution;

import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.github.egatlovs.variable_management_test.process.variables.MyVariables;
import com.github.egatlovs.variablemanager.managers.ExecutionManager;

@Named("executionReadVariableTask")
public class ReadVariablesTask implements JavaDelegate {

	@Inject
	private ExecutionManager manager;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		MyVariables vars = manager.getVariable(MyVariables.class);
		if (vars.getInteger() == 2 && vars.getMap().size() == 0 && vars.getList().size() == 1
				&& vars.getStringField().equals("stringValue")) {
			manager.getExecutionService().setVariable("approved", true);
		} else {
			throw new Exception((vars.getInteger() == 2) + " " + (vars.getMap().size() == 0) + " "
					+ (vars.getList().size() == 1) + " " + (vars.getStringField().equals("stringValue")));
		}
	}

}
