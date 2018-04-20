package com.github.egatlovs.variablemanager.processing;

import java.lang.reflect.Field;

import com.github.egatlovs.variablemanager.StoreStrategies;
import com.github.egatlovs.variablemanager.annotations.Execution;

public class ExecutionAnnotation {

	private final Execution execution;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ExecutionAnnotation(Object o) {
		if (o instanceof Class) {
			this.execution = (Execution) ((Class) o).getAnnotation(Execution.class);
		} else if (o instanceof Field) {
			this.execution = (Execution) ((Field) o).getAnnotation(Execution.class);
		} else {
			this.execution = o.getClass().getAnnotation(Execution.class);
		}
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

}
