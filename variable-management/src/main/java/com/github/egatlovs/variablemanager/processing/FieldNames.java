package com.github.egatlovs.variablemanager.processing;

import com.github.egatlovs.variablemanager.annotations.Ignore;
import com.github.egatlovs.variablemanager.annotations.ObjectValue;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

/**
 * TODO edit documentation
 * <b>FieldNames</b></br>
 * </br>
 * FieldNames is capable of building names out of a given Class. If the Class is
 * annotated with {@code @Execution} the values of the Annotation will be used
 * to determine if the name of the Object or of the Fields of the Object are
 * returned. </br>
 * </br>
 * <b>Note:</b></br>
 * <i>Fields annotated with {@code @Ignore} are ignored and no name will be
 * provided.</i></br>
 *
 * @author egatlovs
 */
public class FieldNames {

    /**
     * TODO edit documentation
     * This method determines names out of a given Class. If the Class is annotated
     * with {@code @Execution} the values of the Annotation will be used to
     * determine if the name of the Object or of the Fields of the Object are
     * returned. </br>
     * </br>
     * <b>Note:</b></br>
     * <i>Fields annotated with {@code @Ignore} are ignored and no name will be
     * provided.</i></br>
     *
     * @param clazz - The Class from which the names will be processed
     * @return The processed names
     */
    public <T> Set<String> getNames(Class<T> clazz) {
        Set<String> names = new HashSet<>();
        if (clazz.isAnnotationPresent(ObjectValue.class)) {
            ObjectValue objectValue = clazz.getAnnotation(ObjectValue.class);
            if (!objectValue.storeFields()) {
                names.add(getObjectName(clazz));
            } else {
                getFieldNames(clazz, names);
            }
        } else {
            names.add(getObjectName(clazz));
        }
        return names;
    }

    private void getFieldNames(Class clazz, Set<String> names) {
        FieldNameExtractor nameExtractor = new FieldNameExtractor();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (!declaredField.isSynthetic() && !declaredField.isAnnotationPresent(Ignore.class)) {
                if (declaredField.isAnnotationPresent(ObjectValue.class)) {
                    ObjectValue objectValue = declaredField.getAnnotation(ObjectValue.class);
                    if (objectValue.storeFields()) {
                        getFieldNames(declaredField.getType(), names);
                    } else {
                        names.add(nameExtractor.getFrom(declaredField));
                    }
                } else {
                    names.add(nameExtractor.getFrom(declaredField));
                }
            }
        }
    }

    private String getObjectName(Class clazz) {
        FieldNameExtractor nameExtractor = new FieldNameExtractor();
        return nameExtractor.getFrom(clazz);
    }

}
