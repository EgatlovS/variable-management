package com.github.egatlovs.variablemanager.managers;

import com.github.egatlovs.variablemanager.processing.FieldNames;
import com.github.egatlovs.variablemanager.processing.ProcessingUnit;
import com.github.egatlovs.variablemanager.processing.ResultObject;
import com.github.egatlovs.variablemanager.validation.VariableValidator;
import org.camunda.bpm.engine.RuntimeService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <b>RuntimeManager</b></br>
 * </br>
 * RuntimeManager wraps a RuntimeService and offers methods to set, get and
 * remove variables based of an ExecutionEntity.</br>
 * The Manager itself uses BeanValidation on each given Object, so you can
 * simply annotate your ExecutionEntities with the well known BeanValidation
 * annotations. </br>
 * </br>
 * The RuntimeManager is injectable. If you inject it in your bean it will
 * initialize itself with the RuntimeManager provided in the environment.
 *
 * @author egatlovs
 */
@SuppressWarnings("CdiInjectionPointsInspection")
@RequestScoped
public class RuntimeManager implements RuntimeVariableManager {

    @Inject
    private RuntimeService runtimeService;

    /**
     * Constructor defining the runtimeService to be used.
     *
     * @param runtimeService - The runtimeService to be used
     */
    public RuntimeManager(RuntimeService runtimeService) {
        this.setExecutionService(runtimeService);
    }

    public RuntimeManager() {
    }

    @Override
    public void setVariable(Object value, String executionid) {
        VariableValidator.validate(value);
        ProcessingUnit punit = new ProcessingUnit();
        Map<String, Object> variables = punit.getVariables(value);
        this.runtimeService.setVariables(executionid, variables);
    }

    @Override
    public void setVariableLocal(Object value, String executionid) {
        VariableValidator.validate(value);
        ProcessingUnit punit = new ProcessingUnit();
        Map<String, Object> variables = punit.getVariables(value);
        this.runtimeService.setVariablesLocal(executionid, variables);
    }

    @Override
    public <T> T getVariable(Class<T> clazz, String executionid) {
        Set<String> variableNames = new FieldNames().getNames(clazz);
        Map<String, Object> variables = new HashMap<>();
        for (String name : variableNames) {
            variables.put(name, this.runtimeService.getVariable(executionid, name));
        }
        return new ResultObject().getValue(clazz, variables);
    }

    @Override
    public <T> T getVariableLocal(Class<T> clazz, String executionid) {
        Set<String> variableNames = new FieldNames().getNames(clazz);
        Map<String, Object> variables = new HashMap<>();
        for (String name : variableNames) {
            variables.put(name, this.runtimeService.getVariableLocal(executionid, name));
        }
        return new ResultObject().getValue(clazz, variables);
    }

    @Override
    public <T> void removeVariables(Class<T> clazz, String executionid) {
        Set<String> variableNames = new FieldNames().getNames(clazz);
        this.runtimeService.removeVariables(executionid, variableNames);
    }

    @Override
    public <T> void removeVariablesLocal(Class<T> clazz, String executionid) {
        Set<String> variableNames = new FieldNames().getNames(clazz);
        this.runtimeService.removeVariablesLocal(executionid, variableNames);
    }

    @Override
    public RuntimeService getExecutionService() {
        return runtimeService;
    }

    @Override
    public void setExecutionService(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

}
