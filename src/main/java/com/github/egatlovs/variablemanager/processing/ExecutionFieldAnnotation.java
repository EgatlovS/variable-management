package com.github.egatlovs.variablemanager.processing;

import com.github.egatlovs.variablemanager.annotations.ExecutionField;

public class ExecutionFieldAnnotation {

	private final ExecutionField executionField;
	private Object annotated;

	public ExecutionFieldAnnotation(Object o) {
		this(o.getClass().getAnnotation(ExecutionField.class), o);
	}

	public ExecutionFieldAnnotation(ExecutionField executionField, Object annotated) {
		this.executionField = executionField;
		this.annotated = annotated;
	}

	public String getName() {
		if (executionField == null) {
			return annotated.getClass().getName();
		}
		return executionField.prefix() + "_" + executionField.name();
	}

}
