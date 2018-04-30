package com.github.egatlovs.variable_management_test.process.task;

import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.github.egatlovs.variable_management_test.process.variables.MyVariables;
import com.github.egatlovs.variablemanager.managers.TaskManager;

@Named("taskRemoveVariableTask")
public class RemoveVariablesTask implements JavaDelegate {

	@Inject
	private TaskManager manager;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		manager.removeVariables(MyVariables.class, manager.getExecutionService().createTaskQuery()
				.processInstanceId(execution.getProcessInstanceId()).singleResult().getId());

		if (manager.getExecutionService().getVariable(manager.getExecutionService().createTaskQuery()
				.processInstanceId(execution.getProcessInstanceId()).singleResult().getId(), "stringField") != null) {
			throw new Exception("variable is still present");
		}
	}

}
