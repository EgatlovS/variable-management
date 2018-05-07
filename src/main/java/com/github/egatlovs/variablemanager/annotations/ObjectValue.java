package com.github.egatlovs.variablemanager.annotations;

import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <b>ObjectValue</b></br>
 * </br>
 * You can annotate fields and classes with {@code @ObjectValue} to manipulate the processing behaviour for the annotated object.
 * The manipulation depends on what attributes are set.</br>
 * 1. If storeFields is set to true:</br>
 * The fields will be stored independent of the object itself.</br>
 * 2. If serializationFormat is set or a custom serializationDataFormat is set:</br>
 * The annotated Field or Object will be written and serialized as if you would use the Camunda TypedValue API.</br>
 * 3. If no attribute is set or no ObjectValue-Annotation is present:</br>
 * Everything will be JAVA-serialized. Just as if you would set SerializationDataFormat.JAVA.</br>
 * </br>
 * Supported Fields are:</br>
 * <ul>
 * <li>serializationDataFormat default JAVA</li>
 * <li>storeFields default false</li>
 * <li>customSerializationDataFormat default ""</li>
 * </ul>
 *
 * @author egatlovs
 */
@Retention(RUNTIME)
@Target({TYPE, FIELD})
public @interface ObjectValue {

    boolean storeFields() default false;

    SerializationDataFormats serializationFormat() default SerializationDataFormats.JAVA;

    String customSerializationFormat() default "";

}
