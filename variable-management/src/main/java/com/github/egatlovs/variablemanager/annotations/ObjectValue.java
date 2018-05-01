package com.github.egatlovs.variablemanager.annotations;

import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.List;
import java.util.Map;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * TODO docu object value
 */
@Retention(RUNTIME)
@Target({ TYPE, FIELD })
public @interface ObjectValue {

    boolean storeFields() default false;
    SerializationDataFormats serializationFormat() default SerializationDataFormats.JAVA;
    String customSerializationFormat() default "";

}
