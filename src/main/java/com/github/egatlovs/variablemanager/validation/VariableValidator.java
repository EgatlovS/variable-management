package com.github.egatlovs.variablemanager.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.github.egatlovs.variablemanager.exceptions.ViolationException;

public class VariableValidator {

	public static void validate(Object value) {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(value);
		if (!constraintViolations.isEmpty()) {
			throw new ViolationException(new ConstraintViolationMessage(constraintViolations));
		}
	}

}
