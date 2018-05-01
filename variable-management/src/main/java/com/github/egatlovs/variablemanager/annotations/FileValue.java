package com.github.egatlovs.variablemanager.annotations;

import org.camunda.bpm.engine.variable.Variables;

/**
 * TODO docu FieldValue can be annotated on inputstream file byte[]
 */
public @interface FileValue {

        String fileName() default "";
        String encoding() default "";
        String mimeType() default "";

}
