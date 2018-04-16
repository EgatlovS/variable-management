package com.github.egatlovs.variablemanager.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;

public class ConstraintViolationMessage {

	private final Set<ConstraintViolation<Object>> constraintViolations;

	public ConstraintViolationMessage(Set<ConstraintViolation<Object>> constraintViolations) {
		this.constraintViolations = constraintViolations;
	}

	public String getMessage() {
		StringBuilder sb = new StringBuilder("Constraint Violations occured: \n");
		for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
			sb.append("Field ").append(constraintViolation.getPropertyPath()).append(" of ")
					.append(constraintViolation.getLeafBean()).append(" ").append(constraintViolation.getMessage())
					.append(" acutal value was ").append(constraintViolation.getInvalidValue()).append("\n");
		}
		return sb.toString();
	}

}
