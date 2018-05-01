package com.github.egatlovs.variablemanager.annotations;

import org.camunda.bpm.engine.variable.Variables.SerializationDataFormats;
import java.util.List;
import java.util.Map;

/**
 * TODO docu object value
 */
public @interface ObjectValue {

    boolean storeField() default false;
    SerializationDataFormats serializationFormat() default SerializationDataFormats.JAVA;
    String customSerializationFormat() default "";

}
