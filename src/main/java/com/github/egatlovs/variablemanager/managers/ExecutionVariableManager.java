package com.github.egatlovs.variablemanager.managers;

public interface ExecutionVariableManager {

	void setVariable(Object value);

	void setVariableLocal(Object value);

	<T> T getVariable(Class<T> clazz);

	<T> T getVariableLocal(Class<T> clazz);

	void removeVariables(Object value);

	void removeVariablesLocal(Object value);

}
