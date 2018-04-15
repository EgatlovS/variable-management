package com.github.egatlovs.variablemanager.managers;

public interface ExecutionVariableManager {

	void setVariable(Object value);

	void setVariableLocal(Object value);

	<T> T getVariable(Class<T> clazz);

	<T> T getVariableLocal(Class<T> clazz);

	<T> void removeVariables(Class<T> clazz);

	<T> void removeVariablesLocal(Class<T> clazz);

}
