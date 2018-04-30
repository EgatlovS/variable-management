package com.github.egatlovs.variable_management_test.process.runtime;

import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.github.egatlovs.variable_management_test.process.variables.MyVariables;
import com.github.egatlovs.variablemanager.managers.RuntimeManager;

@Named("runtimeReadVariableTask")
public class ReadVariablesTask implements JavaDelegate {

	@Inject
	private RuntimeManager manager;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		MyVariables vars = manager.getVariable(MyVariables.class, execution.getProcessInstanceId());
		if (vars.getInteger() == 2 && vars.getMap().size() == 0 && vars.getList().size() == 1
				&& vars.getStringField().equals("stringValue")) {
			manager.getExecutionService().setVariable(execution.getProcessInstanceId(), "approved", true);
		} else {
			throw new Exception((vars.getInteger() == 2) + " " + (vars.getMap().size() == 0) + " "
					+ (vars.getList().size() == 1) + " " + (vars.getStringField().equals("stringValue")));
		}
	}

}
