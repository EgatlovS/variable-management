package com.github.egatlovs.variablemanager.managers;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 * <b>VariableManager</b><br>
 * <br>
 * VariableManager wraps each Manager and offers access to them.<br>
 * The VariableManager is injectable. If you inject it in your bean it will
 * initialize itself with the Managers provided in the environment.
 * <p>
 * For more Information read the documentation of TaskManager, RuntimeManager and ExecutionManager
 *
 * @author egatlovs
 */
@RequestScoped
public class VariableManager {

    @Inject
    private ExecutionVariableManager executionManager;
    @Inject
    private RuntimeVariableManager runtimeManager;
    @Inject
    private TaskVariableManager taskManager;

    public VariableManager(ExecutionVariableManager executionManager, RuntimeVariableManager runtimeManager,
                           TaskVariableManager taskManager) {
        this.executionManager = executionManager;
        this.runtimeManager = runtimeManager;
        this.taskManager = taskManager;
    }

    public VariableManager() {
    }

    public ExecutionVariableManager getExecutionManager() {
        return executionManager;
    }

    public void setExecutionManager(ExecutionVariableManager executionManager) {
        this.executionManager = executionManager;
    }

    public RuntimeVariableManager getRuntimeManager() {
        return runtimeManager;
    }

    public void setRuntimeManager(RuntimeVariableManager runtimeManager) {
        this.runtimeManager = runtimeManager;
    }

    public TaskVariableManager getTaskManager() {
        return taskManager;
    }

    public void setTaskManager(TaskVariableManager taskManager) {
        this.taskManager = taskManager;
    }

}
