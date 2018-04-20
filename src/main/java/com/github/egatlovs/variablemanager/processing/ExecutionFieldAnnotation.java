package com.github.egatlovs.variablemanager.processing;

import com.github.egatlovs.variablemanager.annotations.ExecutionField;

// TODO Refactor if fields or classes are used to instantiate this the wrong values are used
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

	public boolean isFieldAnnotationPresent() {
		return executionField != null;
	}

	public String getName() {
		String name;
		if (executionField == null) {
			name = annotated.getClass().getSimpleName();
		} else if (executionField.prefix() == null || executionField.prefix().isEmpty()) {
			name = executionField.name();
		} else {
			name = executionField.prefix() + "_" + executionField.name();
		}
		return name;
	}

	@Override
	public String toString() {
		return "ExecutionFieldAnnotation [executionField=" + executionField + ", annotated=" + annotated + "]";
	}

}
