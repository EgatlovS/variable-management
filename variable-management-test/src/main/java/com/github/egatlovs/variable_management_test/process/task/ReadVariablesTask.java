package com.github.egatlovs.variable_management_test.process.task;

import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.github.egatlovs.variable_management_test.process.variables.MyVariables;
import com.github.egatlovs.variablemanager.managers.TaskManager;

@Named("taskReadVariableTask")
public class ReadVariablesTask implements JavaDelegate {

	@Inject
	private TaskManager manager;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		MyVariables vars = manager.getVariable(MyVariables.class, manager.getExecutionService().createTaskQuery()
				.processInstanceId(execution.getProcessInstanceId()).singleResult().getId());
		if (vars.getInteger() == 2 && vars.getMap().size() == 0 && vars.getList().size() == 1
				&& vars.getStringField().equals("stringValue")) {
			manager.getExecutionService().setVariable(manager.getExecutionService().createTaskQuery()
					.processInstanceId(execution.getProcessInstanceId()).singleResult().getId(), "approved", true);
		} else {
			throw new Exception((vars.getInteger() == 2) + " " + (vars.getMap().size() == 0) + " "
					+ (vars.getList().size() == 1) + " " + (vars.getStringField().equals("stringValue")));
		}
	}

}
