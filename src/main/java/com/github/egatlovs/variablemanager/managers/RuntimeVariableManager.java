package com.github.egatlovs.variablemanager.managers;

public interface RuntimeVariableManager {

	void setVariable(Object value, String executionid);

	void setVariableLocal(Object value, String executionid);

	<T> T getVariable(Class<T> clazz, String executionid);

	<T> T getVariableLocal(Class<T> clazz, String executionid);

	void removeVariables(Object value, String executionid);

	void removeVariablesLocal(Object value, String executionid);
}
