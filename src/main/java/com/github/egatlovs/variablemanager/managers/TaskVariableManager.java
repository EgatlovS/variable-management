package com.github.egatlovs.variablemanager.managers;

public interface TaskVariableManager {

	void setVariable(Object value, String taskid);

	void setVariableLocal(Object value, String taskid);

	<T> T getVariable(Class<T> clazz, String taskid);

	<T> T getVariableLocal(Class<T> clazz, String taskid);

	<T> void removeVariables(Class<T> clazz, String taskid);

	<T> void removeVariablesLocal(Class<T> clazz, String taskid);

}
