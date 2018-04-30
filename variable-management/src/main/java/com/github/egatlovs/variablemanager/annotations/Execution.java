package com.github.egatlovs.variablemanager.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <b>Execution</b></br>
 * </br>
 * You can annotate classes with {@code @Execution} to change the default
 * processing of ExecutionEntities.</br>
 * If storeFields is set to true (default value) each field will be processed.
 * Otherwise if it's set to false the ProcessingUnit will just handle the Object
 * itself.</br>
 * If storeStrategies is set to OBJECT (default value) everything will be set
 * without specific serialization. If you set storeStrategies to JSON each field
 * or the Object itself will be JSON serialized before its written to the
 * execution. </br>
 * </br>
 * Default values:</br>
 * storeFields = true</br>
 * storeStrategy = Object</br>
 * 
 * @author egatlovs
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface Execution {

	boolean storeFields() default true;

	StoreStrategies storeStrategy() default StoreStrategies.OBJECT;

}
