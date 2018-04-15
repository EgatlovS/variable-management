package com.github.egatlovs.variablemanager.managers;

public interface RuntimeVariableManager {

	void setVariable(Object value, String executionid);

	void setVariableLocal(Object value, String executionid);

	<T> T getVariable(Class<T> clazz, String executionid);

	<T> T getVariableLocal(Class<T> clazz, String executionid);

	<T> void removeVariables(Class<T> clazz, String executionid);

	<T> void removeVariablesLocal(Class<T> clazz, String executionid);
}
