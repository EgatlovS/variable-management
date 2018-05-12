package com.github.egatlovs.variablemanager.validation;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * <b>ConstraintViolationMessage</b><br>
 * <br>
 * ConstraintViolationMessage uses the BeanValidations ConstraintViolations to
 * create a String representing each validation failure of an ExecutionEntity.
 *
 * @author egatlovs
 */
public class ConstraintViolationMessage {

    private final Set<ConstraintViolation<Object>> constraintViolations;

    /**
     * Constructor creating a Constraint Violation Message out of a Set of
     * ConstraintViolations.
     *
     * @param constraintViolations - The violations occured while validating
     */
    public ConstraintViolationMessage(Set<ConstraintViolation<Object>> constraintViolations) {
        this.constraintViolations = constraintViolations;
    }

    /**
     * Returns a message containing each ConstraintViolation in a more readable
     * representation.
     *
     * @return - The formatted ConstraintViolations
     */
    public String getMessage() {
        StringBuilder sb = new StringBuilder("Constraint Violations occured: \n");
        for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
            sb.append("Field ").append(constraintViolation.getPropertyPath()).append(" of ")
                    .append(constraintViolation.getLeafBean()).append(" ").append(constraintViolation.getMessage())
                    .append(" actual value was ").append(constraintViolation.getInvalidValue()).append("\n");
        }
        return sb.toString();
    }

}
