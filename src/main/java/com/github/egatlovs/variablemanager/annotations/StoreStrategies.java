package com.github.egatlovs.variablemanager.annotations;

/**
 * <b>StoreStrategies</b></br>
 * </br>
 * {@code StoreStrategies} defines two values:</br>
 * <b> {@code StoreStrategies.OBJECT}</b> and
 * <b>{@code StoreStrategies.JSON}</b> both of them are typically used in
 * {@code @Execution} to define whether or not a field should be saved as Object
 * or as a TypedValue of JSON inside the execution.
 * 
 * @author egatlovs
 */
public enum StoreStrategies {

	OBJECT, JSON

}
