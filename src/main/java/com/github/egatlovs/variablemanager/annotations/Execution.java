package com.github.egatlovs.variablemanager.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.github.egatlovs.variablemanager.StoreStrategies;

@Retention(RUNTIME)
@Target(TYPE)
public @interface Execution {

	boolean storeFields() default true;

	StoreStrategies storeStartegy() default StoreStrategies.OBJECT;

}
