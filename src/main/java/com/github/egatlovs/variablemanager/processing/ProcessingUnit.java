package com.github.egatlovs.variablemanager.processing;

import com.github.egatlovs.variablemanager.annotations.FileValue;
import com.github.egatlovs.variablemanager.annotations.Ignore;
import com.github.egatlovs.variablemanager.annotations.ObjectValue;
import com.github.egatlovs.variablemanager.exceptions.ExceptionHandler;
import com.github.egatlovs.variablemanager.exceptions.UnsupportedFileTypeException;
import com.github.egatlovs.variablemanager.exceptions.VariableProcessingException;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.builder.FileValueBuilder;
import org.camunda.bpm.engine.variable.value.builder.ObjectValueBuilder;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;

/**
 * <b>ProcessingUnit</b></br>
 * </br>
 * ProcessingUnit is capable of extracting variables from ExecutionEntitys.</br>
 * You can manipulate the processing of Objects and Field by using following annotations:
 * </br>
 * <ul>
 * <li>Ignore</li>
 * <li>ObjectValue</li>
 * <li>FileValue</li>
 * <li>FieldName</li>
 * </ul>
 * </br>
 * <p>
 * For more Information see documentation for the given annotations.</br>
 * </br>
 *
 * @author egatlovs
 */
public class ProcessingUnit {

    private final FieldNameExtractor fieldNameExtractor;

    public ProcessingUnit() {
        this.fieldNameExtractor = new FieldNameExtractor();
    }

    /**
     * Retrieves execution variables from an Object.
     * This method processes the Object and watches out for manipulating
     * annotations like:</br>
     * <ul>
     * <li>Ignore</li>
     * <li>ObjectValue</li>
     * <li>FileValue</li>
     * <li>FieldName</li>
     * </ul>
     * If one of them is present the processing reacts to it and
     * extracts variables in a different way.
     * For more information read the documentation of the given annotations.
     * </br>
     *
     * @param obj The Object variables should be processed from
     * @return The variables processed
     * @throws VariableProcessingException if something goes wrong
     */
    public VariableMap getVariables(Object obj) {
        final VariableMap variableMap = Variables.createVariables();
        if (obj.getClass().isAnnotationPresent(ObjectValue.class)) {
            ObjectValue objectValue = obj.getClass().getAnnotation(ObjectValue.class);
            if (!objectValue.storeFields()) {
                getObjectValue(obj, variableMap, objectValue);
            } else {
                getNestedFields(obj, variableMap);
            }
        } else {
            getObjectValue(obj, variableMap, null);
        }
        return variableMap;

    }

    private void getNestedFields(Object obj, VariableMap variableMap) {
        final Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (!declaredField.isSynthetic() && !declaredField.isAnnotationPresent(Ignore.class)) {
                declaredField.setAccessible(true);
                try {
                    if (declaredField.isAnnotationPresent(ObjectValue.class)) {
                        ObjectValue nestedObjectValue = declaredField.getAnnotation(ObjectValue.class);
                        if (nestedObjectValue.storeFields()) {
                            Object nestedObject = declaredField.get(obj);
                            if (nestedObject != null) {
                                getNestedFields(declaredField.get(obj), variableMap);
                            }
                        } else {
                            getObjectValue(declaredField.get(obj), variableMap, nestedObjectValue);
                        }
                    } else if (declaredField.isAnnotationPresent(FileValue.class)) {
                        getFileValue(obj, variableMap, declaredField);
                    } else {
                        getFieldValue(obj, variableMap, declaredField);
                    }

                } catch (IllegalAccessException e) {
                    ExceptionHandler.createVariableProcessingException(e, declaredField, obj);
                }
            }
        }
    }

    private void getFieldValue(Object obj, VariableMap variableMap, Field declaredField) throws IllegalAccessException {
        String name = fieldNameExtractor.getFrom(declaredField);
        variableMap.putValue(name, declaredField.get(obj));
    }

    private void getFileValue(Object obj, VariableMap variableMap, Field declaredField) throws IllegalAccessException {
        FileValue fileValue = declaredField.getAnnotation(FileValue.class);
        String name = fieldNameExtractor.getFrom(declaredField);
        FileValueBuilder fileValueBuilder = Variables.fileValue(fileValue.fileName()).encoding(fileValue.encoding()).mimeType(fileValue.mimeType());
        Object fieldValue = declaredField.get(obj);
        if(fieldValue == null){
            variableMap.putValue(name, fileValueBuilder.create());
        } else if (declaredField.getType().isAssignableFrom(File.class)) {
            variableMap.putValue(name, fileValueBuilder.file((File) fieldValue).create());
        } else if (declaredField.getType().isAssignableFrom(InputStream.class)) {
            variableMap.putValue(name, fileValueBuilder.file((InputStream) fieldValue).create());
        } else if (declaredField.getType().isAssignableFrom(byte[].class)) {
            variableMap.putValue(name, fileValueBuilder.file((byte[]) fieldValue).create());
        } else {
            throw new UnsupportedFileTypeException("Annotation FileValue was set on an unsupported fileType. Supported FileTypes are byte[], File and InputStream. Check Field with name " + declaredField.getName() + ". Type was " + declaredField.getType());
        }
    }

    private void getObjectValue(Object obj, VariableMap variableMap, ObjectValue objectValue) {
        if (obj != null) {
            String objectName = fieldNameExtractor.getFrom(obj);
            ObjectValueBuilder objectValueBuilder = Variables.objectValue(obj);
            if (objectValue == null) {
                variableMap.putValue(objectName, objectValueBuilder.create());
            } else if (objectValue.customSerializationFormat().isEmpty()) {
                variableMap.putValue(objectName, objectValueBuilder.serializationDataFormat(objectValue.serializationFormat()).create());
            } else {
                variableMap.putValue(objectName, objectValueBuilder.serializationDataFormat(objectValue.customSerializationFormat()).create());
            }
        }
    }
}