package com.github.egatlovs.variablemanager.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <b>Ignore</b></br>
 * </br>
 * You can annotate fields with {@code @Ignore} to tell the processing unit to
 * ignore it.</br>
 * <p>
 * If a field is annotated with {@code @Ignore} the processing unit will not
 * read or write the value of the field except as long as {@code @ObjectValue} is set and the attribute storeFields is set to true.
 * Otherwise the field cannot be ignored because the whole object will be written to the execution.
 * </br></br>
 *
 * @author egatlovs
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface Ignore {

}
