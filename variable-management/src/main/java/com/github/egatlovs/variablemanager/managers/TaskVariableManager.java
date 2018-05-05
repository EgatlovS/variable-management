package com.github.egatlovs.variablemanager.managers;

import org.camunda.bpm.engine.TaskService;

public interface TaskVariableManager {

    /**
     * Sets a Variable to the Execution. </br>
     * </br>
     * <b>Note:</b> the variable will be processed as follows:
     * <ol>
     * <li>The object will be validated using bean validation.</li>
     * <li>The given object will be processed using
     * {@code VariableProcessor.class}</li>
     * </ol>
     * If you want more Information of how variables are processed watch out for
     * {@code VariableProcessor.class}. </br>
     * If you want to manipulate how your variables are processed look for
     * {@code @Execution}, {@code @ExecutionField} and {@code @Ignore}. </br>
     * </br>
     *
     * @param value  - The value to be set to the Execution
     * @param taskid - The id of the task to be used
     */
    void setVariable(Object value, String taskid);

    /**
     * Sets a Variable Locally to the Execution. </br>
     * </br>
     * <b>Note:</b> the variable will be processed as follows:
     * <ol>
     * <li>The object will be validated using bean validation.</li>
     * <li>The given object will be processed using
     * {@code VariableProcessor.class}</li>
     * </ol>
     * If you want more Information of how variables are processed watch out for
     * {@code VariableProcessor.class}. </br>
     * If you want to manipulate how your variables are processed look for
     * {@code @Execution}, {@code @ExecutionField} and {@code @Ignore}. </br>
     * </br>
     *
     * @param value  - The value to be set to the Execution
     * @param taskid - The id of the task to be used
     */
    void setVariableLocal(Object value, String taskid);

    /**
     * Retrieves a Variable from the Execution. </br>
     * </br>
     * The variables will be read from the given class using
     * {@code FieldNames.class}. After that each variable will be called out of the
     * execution. </br>
     * Now that each variable is retrieved from the execution, the
     * {@code ResultObject.class} will be used to build the requested object which
     * is then returned. </br>
     *
     * @param clazz  - The Class which should be gathered
     * @param taskid - The id of the task to be used
     * @return - The Object of the requested class
     */
    <T> T getVariable(Class<T> clazz, String taskid);

    /**
     * Retrieves a Variable locally from the Execution. </br>
     * </br>
     * The variables will be read from the given class using
     * {@code FieldNames.class}. After that each variable will be called out of the
     * execution. </br>
     * Now that each variable is retrieved from the execution, the
     * {@code ResultObject.class} will be used to build the requested object which
     * is then returned. </br>
     *
     * @param clazz  - The Class which should be gathered
     * @param taskid - The id of the task to be used
     * @return - The Object of the requested class
     */
    <T> T getVariableLocal(Class<T> clazz, String taskid);

    /**
     * Removes a Variable from the Execution. </br>
     * </br>
     * The variables will be read from the given class using
     * {@code FieldNames.class}. After that each variable will be removed from the
     * execution. </br>
     *
     * @param clazz  - The Class which should be used to retrieve the fieldnames to be
     *               deleted
     * @param taskid - The id of the task to be used
     */
    <T> void removeVariables(Class<T> clazz, String taskid);

    /**
     * Removes Variables locally from the Execution. </br>
     * </br>
     * The variables will be read from the given class using
     * {@code FieldNames.class}. After that each variable will be removed from the
     * execution. </br>
     *
     * @param clazz  - The Class which should be used to retrieve the fieldnames to be
     *               deleted
     * @param taskid - The id of the task to be used
     */
    <T> void removeVariablesLocal(Class<T> clazz, String taskid);

    /**
     * Gives access to the wrapped taskService.
     *
     * @return Returns the wrapped taskService
     */
    TaskService getExecutionService();

    /**
     * Manipulate the taskService used.
     *
     * @param taskService - The new taskService to be used
     */
    void setExecutionService(TaskService taskService);

}
