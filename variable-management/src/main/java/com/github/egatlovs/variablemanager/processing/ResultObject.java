package com.github.egatlovs.variablemanager.processing;

import com.github.egatlovs.variablemanager.annotations.Ignore;
import com.github.egatlovs.variablemanager.annotations.ObjectValue;
import com.github.egatlovs.variablemanager.exceptions.ExceptionHandler;
import com.github.egatlovs.variablemanager.exceptions.ResultObjectException;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * <b>ResultObject</b></br>
 * </br>
 * ResultObject represents an ExecutionEntity.</br>
 * After the variables of a given Class are gathered from the Execution, a
 * ResultObject can be created. It takes those variables and a Class which it
 * should represent. This class then instantiates the given Class and maps the
 * variables to it. </br>
 *
 * @author egatlovs
 */
@SuppressWarnings("JavaDoc")
public class ResultObject {

    /**
     * TODO edit documentation
     * GetValue creates an Object of the given Class and maps all the given
     * variables to the corresponding Fields.
     *
     * @param clazz     - The class to be created
     * @param variables - The variables to be mapped
     * @return The created Object
     * @throws ResultObjectException
     */
    public <T> T getValue(Class<T> clazz, Map<String, Object> variables) {
        T obj = instantiateObject(clazz);

        FieldNameExtractor fieldNameExtractor = new FieldNameExtractor();
        if (clazz.isAnnotationPresent(ObjectValue.class)) {
            ObjectValue objectValue = clazz.getAnnotation(ObjectValue.class);
            if (!objectValue.storeFields()) {
                T result = getFromObjectValue(variables);
                if (result == null) {
                    throw new ResultObjectException("Multiple values were found but Object of type " + clazz.getName() + " has store Fields set to false on Annotation ObjectValue");
                }
                return result;
            } else {
                getFieldValues(clazz, variables, obj, fieldNameExtractor);
            }
        } else {
            T result = getFromObjectValue(variables);
            if (result == null) {
                throw new ResultObjectException("Multiple values were found but Object of type " + clazz.getName() + " is not annotated with Object Value and store Fields is therefor false");
            }
            return result;
        }
        return obj;
    }

    private <T> void getFieldValues(Class clazz, Map<String, Object> variables, Object obj, FieldNameExtractor fieldNameExtractor) {
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (!declaredField.isSynthetic() && !declaredField.isAnnotationPresent(Ignore.class)) {
                declaredField.setAccessible(true);
                try {
                    if (declaredField.isAnnotationPresent(ObjectValue.class)) {
                        ObjectValue objectValue = declaredField.getAnnotation(ObjectValue.class);
                        if (objectValue.storeFields()) {
                            Object nestedObject = instantiateObject(declaredField.getType());
                            getFieldValues(declaredField.getType(), variables, nestedObject, fieldNameExtractor);
                            declaredField.set(obj, nestedObject);
                        } else {
                            declaredField.set(obj, variables.get(fieldNameExtractor.getFrom(declaredField)));
                        }
                    } else {
                        declaredField.set(obj, variables.get(fieldNameExtractor.getFrom(declaredField)));
                    }
                } catch (IllegalAccessException e) {
                    ExceptionHandler.createResultObjectException(e, clazz);
                }
            }
        }
    }

    private <T> T getFromObjectValue(Map<String, Object> variables) {
        if (variables.size() == 1) {
            for (Object value : variables.values()) {
                return (T) value;
            }
        }
        return null;
    }

    private <T> T instantiateObject(Class<T> clazz) {
        T obj = null;
        try {
            obj = clazz.getConstructor().newInstance();
        } catch (Exception e) {
            ExceptionHandler.createResultObjectException(e, clazz);
        }
        return obj;
    }

}
