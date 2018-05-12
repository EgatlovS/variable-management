package com.github.egatlovs.variablemanager.managers;

import com.github.egatlovs.variablemanager.processing.FieldNames;
import com.github.egatlovs.variablemanager.processing.ProcessingUnit;
import com.github.egatlovs.variablemanager.processing.ResultObject;
import com.github.egatlovs.variablemanager.validation.VariableValidator;
import org.camunda.bpm.engine.delegate.DelegateExecution;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <b>ExecutionManager</b><br>
 * <br>
 * ExecutionManager wraps a DelegateExecution and offers methods to set, get and
 * remove variables based of an ExecutionEntity.<br>
 * The Manager itself uses BeanValidation on each given Object, so you can
 * simply annotate your ExecutionEntities with the well known BeanValidation
 * annotations. <br>
 * <br>
 * The ExecutionManager is injectable. If you inject it in your bean it will
 * initialize itself with the current delegate execution if present. Otherwise
 * if no execution is present the Manager will initialize itself with null.
 *
 * @author egatlovs
 */
@RequestScoped
public class ExecutionManager implements ExecutionVariableManager {

    @Inject
    private DelegateExecution execution;

    /**
     * Constructor defining the execution to be used.
     *
     * @param execution - The execution to be used
     */
    public ExecutionManager(DelegateExecution execution) {
        this.execution = execution;
    }

    public ExecutionManager() {
    }

    @Override
    public void setVariable(Object value) {
        VariableValidator.validate(value);
        ProcessingUnit punit = new ProcessingUnit();
        Map<String, Object> variables = punit.getVariables(value);
        this.execution.setVariables(variables);
    }

    @Override
    public void setVariableLocal(Object value) {
        VariableValidator.validate(value);
        ProcessingUnit punit = new ProcessingUnit();
        Map<String, Object> variables = punit.getVariables(value);
        this.execution.setVariables(variables);
    }

    @Override
    public <T> T getVariable(Class<T> clazz) {
        Set<String> variableNames = new FieldNames().getNames(clazz);
        Map<String, Object> variables = new HashMap<>();
        for (String name : variableNames) {
            variables.put(name, this.execution.getVariable(name));
        }
        return new ResultObject().getValue(clazz, variables);
    }

    @Override
    public <T> T getVariableLocal(Class<T> clazz) {
        Set<String> variableNames = new FieldNames().getNames(clazz);
        Map<String, Object> variables = new HashMap<>();
        for (String name : variableNames) {
            variables.put(name, this.execution.getVariableLocal(name));
        }
        return new ResultObject().getValue(clazz, variables);
    }

    @Override
    public <T> void removeVariables(Class<T> clazz) {
        Set<String> variableNames = new FieldNames().getNames(clazz);
        this.execution.removeVariables(variableNames);
    }

    @Override
    public <T> void removeVariablesLocal(Class<T> clazz) {
        Set<String> variableNames = new FieldNames().getNames(clazz);
        this.execution.removeVariablesLocal(variableNames);
    }

    @Override
    public DelegateExecution getExecutionService() {
        return execution;
    }

    @Override
    public void setExecutionService(DelegateExecution execution) {
        this.execution = execution;
    }

}
