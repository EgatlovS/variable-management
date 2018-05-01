package com.github.egatlovs.variablemanager.processing;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import com.github.egatlovs.variablemanager.annotations.Execution;
import com.github.egatlovs.variablemanager.annotations.Ignore;

/**
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
	 * This method determines names out of a given Class. If the Class is annotated
	 * with {@code @Execution} the values of the Annotation will be used to
	 * determine if the name of the Object or of the Fields of the Object are
	 * returned. </br>
	 * </br>
	 * <b>Note:</b></br>
	 * <i>Fields annotated with {@code @Ignore} are ignored and no name will be
	 * provided.</i></br>
	 * 
	 * @param clazz
	 *            - The Class from which the names will be processed
	 * @return The processed names
	 */
	public <T> Set<String> get(Class<T> clazz) {
		Set<String> names;
		ExecutionAnnotation execution = new ExecutionAnnotation(clazz.getAnnotation(Execution.class));
		if (execution.isStoreFields()) {
			names = getNamesFromFields(clazz);
		} else {
			names = getObjectName(clazz);
		}
		return names;
	}

	private <T> Set<String> getObjectName(Class<T> clazz) {
		Set<String> names = new HashSet<>();
		FieldNameExtractor fieldName = new FieldNameExtractor();
		names.add(fieldName.getFrom(clazz));
		return names;
	}

	private <T> Set<String> getNamesFromFields(Class<T> clazz) {
		Set<String> names = new HashSet<>();
		Field[] fields = clazz.getDeclaredFields();
		FieldNameExtractor fieldName = new FieldNameExtractor();
		for (Field field : fields) {
			if (!field.isSynthetic() && !field.isAnnotationPresent(Ignore.class)) {
				names.add(fieldName.getFrom(field));
			}
		}
		return names;
	}

}
