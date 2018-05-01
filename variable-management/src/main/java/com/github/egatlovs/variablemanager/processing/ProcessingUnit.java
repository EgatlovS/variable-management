package com.github.egatlovs.variablemanager.processing;

import com.github.egatlovs.variablemanager.annotations.FieldName;
import com.github.egatlovs.variablemanager.annotations.FileValue;
import com.github.egatlovs.variablemanager.annotations.Ignore;
import com.github.egatlovs.variablemanager.annotations.ObjectValue;
import com.github.egatlovs.variablemanager.exceptions.ExceptionHandler;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.builder.FileValueBuilder;
import org.camunda.bpm.engine.variable.value.builder.ObjectValueBuilder;
import org.camunda.bpm.model.dmn.instance.Variable;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Map;

public class ProcessingUnit {

    private FieldNameExtractor fieldNameExtractor;

    public ProcessingUnit() {
        this.fieldNameExtractor = new FieldNameExtractor();
    }

    public VariableMap getVariables(Object obj) {
        VariableMap variableMap = Variables.createVariables();
        if (obj.getClass().isAnnotationPresent(ObjectValue.class)) {
            ObjectValue objectValue = obj.getClass().getAnnotation(ObjectValue.class);
            if (!objectValue.storeFields()) {
                getObjectValue(obj, variableMap, objectValue);
            } else {
                Field[] declaredFields = obj.getClass().getDeclaredFields();
                for (Field declaredField : declaredFields) {
                    if (!declaredField.isSynthetic() && !declaredField.isAnnotationPresent(Ignore.class)) {
                        declaredField.setAccessible(true);
                        if (declaredField.isAnnotationPresent(ObjectValue.class)) {
                            variableMap.putAll(this.getVariables(declaredField));
                        } else if (declaredField.isAnnotationPresent(FileValue.class)) {
                            try {
                                getFileValue(obj, variableMap, declaredField);
                            } catch (IllegalAccessException e) {
                                ExceptionHandler.createVariableProcessingException(e, declaredField, obj);
                            }
                        } else {
                            try {
                                getFieldValue(obj, variableMap, declaredField);
                            } catch (IllegalAccessException e) {
                                ExceptionHandler.createVariableProcessingException(e, declaredField, obj);
                            }
                        }
                    }
                }
            }
        } else {
            getObjectValue(obj, variableMap, null);
        }
        return variableMap;

    }

    private void getFieldValue(Object obj, VariableMap variableMap, Field declaredField) throws IllegalAccessException {
        String name = fieldNameExtractor.getFrom(declaredField);
        variableMap.putValue(name, declaredField.get(obj));
    }

    private void getFileValue(Object obj, VariableMap variableMap, Field declaredField) throws IllegalAccessException {
        FileValue fileValue = declaredField.getAnnotation(FileValue.class);
        String name = fieldNameExtractor.getFrom(declaredField);
        Object fieldValue = declaredField.get(obj);
        FileValueBuilder fileValueBuilder = Variables.fileValue(fileValue.fileName()).encoding(fileValue.encoding()).mimeType(fileValue.mimeType());
        if (fieldValue instanceof File) {
            variableMap.putValue(name, fileValueBuilder.file((File) fieldValue).create());
        } else if (fieldValue instanceof InputStream) {
            variableMap.putValue(name, fileValueBuilder.file((InputStream) fieldValue).create());
        } else if (fieldValue instanceof byte[]) {
            variableMap.putValue(name, fileValueBuilder.file((byte[]) fieldValue).create());
        } else {
            // TODO throw exception unsupported file type
        }
    }

    private void getObjectValue(Object obj, VariableMap variableMap, ObjectValue objectValue) {
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