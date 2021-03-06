package com.github.egatlovs.variablemanager.managers;

import com.github.egatlovs.variablemanager.processing.FieldNames;
import com.github.egatlovs.variablemanager.processing.ProcessingUnit;
import com.github.egatlovs.variablemanager.processing.ResultObject;
import com.github.egatlovs.variablemanager.validation.VariableValidator;
import org.camunda.bpm.engine.TaskService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <b>TaskManager</b><br>
 * <br>
 * TaskManager wraps a TaskService and offers methods to set, get and remove
 * variables based of an ExecutionEntity.<br>
 * The Manager itself uses BeanValidation on each given Object, so you can
 * simply annotate your ExecutionEntities with the well known BeanValidation
 * annotations. <br>
 * <br>
 * The TaskManager is injectable. If you inject it in your bean it will
 * initialize itself with the TaskManager provided in the environment.
 *
 * @author egatlovs
 */
@SuppressWarnings("CdiInjectionPointsInspection")
@RequestScoped
public class TaskManager implements TaskVariableManager {

    @Inject
    private TaskService taskService;

    /**
     * Constructor defining the taskService to be used.
     *
     * @param taskService - The taskService to be used
     */
    public TaskManager(TaskService taskService) {
        this.taskService = taskService;
    }

    public TaskManager() {
    }

    @Override
    public void setVariable(Object value, String taskid) {
        VariableValidator.validate(value);
        ProcessingUnit punit = new ProcessingUnit();
        Map<String, Object> variables = punit.getVariables(value);
        this.taskService.setVariables(taskid, variables);
    }

    @Override
    public void setVariableLocal(Object value, String taskid) {
        VariableValidator.validate(value);
        ProcessingUnit punit = new ProcessingUnit();
        Map<String, Object> variables = punit.getVariables(value);
        this.taskService.setVariables(taskid, variables);
    }

    @Override
    public <T> T getVariable(Class<T> clazz, String taskid) {
        Set<String> variableNames = new FieldNames().getNames(clazz);
        Map<String, Object> variables = new HashMap<>();
        for (String name : variableNames) {
            variables.put(name, this.taskService.getVariable(taskid, name));
        }
        return new ResultObject().getValue(clazz, variables);
    }

    @Override
    public <T> T getVariableLocal(Class<T> clazz, String taskid) {
        Set<String> variableNames = new FieldNames().getNames(clazz);
        Map<String, Object> variables = new HashMap<>();
        for (String name : variableNames) {
            variables.put(name, this.taskService.getVariableLocal(taskid, name));
        }
        return new ResultObject().getValue(clazz, variables);
    }

    @Override
    public <T> void removeVariables(Class<T> clazz, String taskid) {
        Set<String> variableNames = new FieldNames().getNames(clazz);
        this.taskService.removeVariables(taskid, variableNames);
    }

    @Override
    public <T> void removeVariablesLocal(Class<T> clazz, String taskid) {
        Set<String> variableNames = new FieldNames().getNames(clazz);
        this.taskService.removeVariablesLocal(taskid, variableNames);
    }

    @Override
    public TaskService getExecutionService() {
        return taskService;
    }

    @Override
    public void setExecutionService(TaskService taskService) {
        this.taskService = taskService;
    }

}
