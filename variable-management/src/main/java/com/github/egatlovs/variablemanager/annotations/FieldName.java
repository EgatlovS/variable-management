package com.github.egatlovs.variablemanager.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * TODO edit documentation
 * <b>ExecutionField</b></br>
 * </br>
 * You can annotate fields and classes with {@code @ExecutionField} to set a
 * custom name and prefix. The processing unit will recognize this and use that
 * name for the Field name in the execution.</br>
 * If name is set it will be used for the actual name of the field inside the
 * execution.</br>
 * </br>
 * <b>Note</b><i> that if you use this Annotation the name is required. If you
 * don't annotate a field which will be processed the actual name of the field
 * will be used.</i></br>
 * </br>
 * If prefix is set to some string the execution field will be named with the
 * prefix, an underscore and the specified name afterwards. </br>
 * </br>
 * <b>Example: </b></br>
 * {@code prefix + "_" + name}</br>
 * </br>
 * If prefix is set to null or is an empty String it will be ignored and the
 * execution field will be named with the name value you specified.</br>
 * </br>
 * Default values:</br>
 * name has no default value</br>
 * prefix = null</br>
 *
 * @author egatlovs
 */
@Retention(RUNTIME)
@Target({TYPE, FIELD})
public @interface FieldName {

    String name();

    String prefix() default "";

}
