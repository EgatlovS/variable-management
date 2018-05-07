package com.github.egatlovs.variablemanager.processing;

import com.github.egatlovs.variablemanager.annotations.FieldName;

import java.lang.reflect.Field;

/**
 * <b>FieldNameExtractor</b></br>
 * </br>
 * FieldNameExtractor is capable of building a name of a given Field, Class or Object.
 *
 * @author egatlovs
 */
public class FieldNameExtractor {

    /**
     * Returns the name of the given Field. If the Field is annotated with
     * {@code @FieldName} the values will be recognized and used to provide the right
     * name.
     *
     * @param field - The field to retrieve the name from
     * @return The name of the given field
     */
    public String getFrom(Field field) {
        FieldName executionField = field.getAnnotation(FieldName.class);
        String name;
        if (executionField == null) {
            name = field.getName();
        } else {
            executionField.prefix();
            if (executionField.prefix().isEmpty()) {
                name = executionField.name();
            } else {
                name = executionField.prefix() + "_" + executionField.name();
            }
        }
        return name;
    }

    /**
     * Returns the name of the given Class. If the Class is annotated with
     * {@code @FieldName} the values will be recognized and used to provide the right
     * name.
     *
     * @param clazz - The Class to retrieve the name from
     * @return The name of the given Class
     */
    public String getFrom(@SuppressWarnings("rawtypes") Class clazz) {
        @SuppressWarnings("unchecked")
        FieldName executionField = (FieldName) clazz.getAnnotation(FieldName.class);
        String name;
        if (executionField == null) {
            name = clazz.getSimpleName();
        } else {
            executionField.prefix();
            if (executionField.prefix().isEmpty()) {
                name = executionField.name();
            } else {
                name = executionField.prefix() + "_" + executionField.name();
            }
        }
        return name;
    }

    /**
     * Returns the name of the given Object. If the Object is annotated with
     * {@code @FieldName} the values will be recognized and used to provide the right
     * name.
     *
     * @param o - The Object to retrieve the name from
     * @return The name of the given Object
     */
    public String getFrom(Object o) {
        FieldName executionField = o.getClass().getAnnotation(FieldName.class);
        String name;
        if (executionField == null) {
            name = o.getClass().getSimpleName();
        } else {
            executionField.prefix();
            if (executionField.prefix().isEmpty()) {
                name = executionField.name();
            } else {
                name = executionField.prefix() + "_" + executionField.name();
            }
        }
        return name;
    }

}
