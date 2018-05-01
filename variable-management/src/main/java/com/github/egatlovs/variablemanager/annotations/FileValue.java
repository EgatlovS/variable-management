package com.github.egatlovs.variablemanager.annotations;

import org.camunda.bpm.engine.variable.Variables;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * TODO docu FieldValue can be annotated on inputstream file byte[]
 */
@Retention(RUNTIME)
@Target({ FIELD })
public @interface FileValue {

        String fileName() default "";
        String encoding() default "";
        String mimeType() default "";

}
