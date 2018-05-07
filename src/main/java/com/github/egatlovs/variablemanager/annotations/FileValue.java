package com.github.egatlovs.variablemanager.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <b>FileValue</b></br>
 * </br>
 * You can annotate Inputstreams, byte-arrays and Files with {@code @FileValue} to set
 * some metadata. This data is the same you could set on a FileValue supported by the TypedValue API by Camunda.</br>
 * The Field annotated with {@code @FileValue} will be recognized by the Processing Units used inside the Manager-Classes.
 * So if you annotate some field with this it will be stored as if you would store it with the TypedValue API.</br>
 * </br>
 * Supported Fields are:</br>
 * <ul>
 * <li>fileName</li>
 * <li>encoding</li>
 * <li>mimeType</li>
 * </ul>
 *
 * @author egatlovs
 */
@Retention(RUNTIME)
@Target({FIELD})
public @interface FileValue {

    String fileName() default "";

    String encoding() default "";

    String mimeType() default "";

}
