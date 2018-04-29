package com.github.egatlovs.variable_management_test.process.runtime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.github.egatlovs.variable_management_test.process.variables.MyVariables;
import com.github.egatlovs.variablemanager.managers.RuntimeManager;

@Named("runtimeCreateVariableTask")
public class CreateVariablesTask implements JavaDelegate {

	@Inject
	private RuntimeManager manager;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		List<String> list = new ArrayList<String>();
		list.add("stringOne");
		Map<String, Object> map = new HashMap<String, Object>();

		MyVariables vars = new MyVariables("stringValue", 2, list, map);
		manager.setVariable(vars, execution.getProcessInstanceId());
	}

}
