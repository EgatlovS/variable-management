package com.github.egatlovs.variablemanager.processing;

import com.github.egatlovs.variablemanager.StoreStrategies;
import com.github.egatlovs.variablemanager.annotations.Execution;

public class ExecutionAnnotation {

	private final Execution execution;

	public ExecutionAnnotation(Object o) {
		this(o.getClass().getAnnotation(Execution.class));
	}

	public ExecutionAnnotation(Execution execution) {
		this.execution = execution;
	}

	public StoreStrategies getStoreStrategy() {
		if (execution == null) {
			return StoreStrategies.OBJECT;
		}
		return execution.storeStartegy();
	}

	public boolean isStoreFields() {
		if (execution == null) {
			return true;
		}
		return execution.storeFields();
	}

	@Override
	public String toString() {
		return "ExecutionAnnotation [execution=" + execution + "]";
	}

}
