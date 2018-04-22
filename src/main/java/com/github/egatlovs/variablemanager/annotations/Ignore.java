package com.github.egatlovs.variablemanager.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <b>Ignore</b></br>
 * </br>
 * You can annotate fields with {@code @Ignore} to tell the processing unit to
 * ignore it.</br>
 * 
 * If a field is annotated with {@code @Ignore} the processing unit will not
 * read or write the value of the field except the Object itself is annotated
 * with {@code @Execution} and the value of storeFields is set to false.</br>
 * If a field is annotated with {@code @Execution} and the value of storeFields
 * is set to false the whole Object will be written and the {@code @Ignore}
 * Annotation will be ignored itself.</br>
 * </br>
 * 
 * @author egatlovs
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface Ignore {

}
