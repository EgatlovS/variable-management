package com.github.egatlovs.variablemanager.validation;

import com.github.egatlovs.variablemanager.exceptions.ViolationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * <b>VariableValidator</b><br>
 * <br>
 * This class wraps the BeanValidation Feature in a single Method.
 *
 * @author egatlovs
 */
public class VariableValidator {

    /**
     * This method uses BeanValidation on the given Object. If no
     * ConstraintViolation is present after validating the object the method just
     * finishes. If a ConstraintViolation or more are returned the method throws a
     * ViolationException with each validation failure in its message.
     *
     * @param value - The object to be validated
     * @throws ViolationException which contains detailed Information on what went wrong
     */
    public static void validate(Object value) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(value);
        if (!constraintViolations.isEmpty()) {
            throw new ViolationException(new ConstraintViolationMessage(constraintViolations));
        }
    }

}
