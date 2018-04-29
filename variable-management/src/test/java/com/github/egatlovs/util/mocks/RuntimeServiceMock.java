package com.github.egatlovs.util.mocks;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.batch.Batch;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.migration.MigrationPlan;
import org.camunda.bpm.engine.migration.MigrationPlanBuilder;
import org.camunda.bpm.engine.migration.MigrationPlanExecutionBuilder;
import org.camunda.bpm.engine.runtime.ActivityInstance;
import org.camunda.bpm.engine.runtime.EventSubscriptionQuery;
import org.camunda.bpm.engine.runtime.ExecutionQuery;
import org.camunda.bpm.engine.runtime.Incident;
import org.camunda.bpm.engine.runtime.IncidentQuery;
import org.camunda.bpm.engine.runtime.MessageCorrelationBuilder;
import org.camunda.bpm.engine.runtime.ModificationBuilder;
import org.camunda.bpm.engine.runtime.NativeExecutionQuery;
import org.camunda.bpm.engine.runtime.NativeProcessInstanceQuery;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.runtime.ProcessInstanceModificationBuilder;
import org.camunda.bpm.engine.runtime.ProcessInstanceQuery;
import org.camunda.bpm.engine.runtime.ProcessInstantiationBuilder;
import org.camunda.bpm.engine.runtime.RestartProcessInstanceBuilder;
import org.camunda.bpm.engine.runtime.SignalEventReceivedBuilder;
import org.camunda.bpm.engine.runtime.UpdateProcessInstanceSuspensionStateSelectBuilder;
import org.camunda.bpm.engine.runtime.VariableInstanceQuery;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.value.TypedValue;

import com.github.egatlovs.util.exceptions.NotImplementedExecption;

public class RuntimeServiceMock implements RuntimeService {

	private DelegateExecution execution;

	public RuntimeServiceMock(DelegateExecution execution) {
		this.execution = execution;
	}

	@Override
	public void setVariable(String executionId, String variableName, Object value) {
		this.execution.setVariable(variableName, value);
	}

	@Override
	public void setVariables(String executionId, Map<String, ? extends Object> execution) {
		this.execution.setVariables(execution);
	}

	@Override
	public void setVariableLocal(String executionId, String variableName, Object value) {
		this.execution.setVariableLocal(variableName, value);
	}

	@Override
	public void setVariablesLocal(String executionId, Map<String, ? extends Object> execution) {
		this.execution.setVariablesLocal(execution);
	}

	@Override
	public Object getVariable(String executionId, String variableName) {
		return this.execution.getVariable(variableName);
	}

	@Override
	public <T extends TypedValue> T getVariableTyped(String executionId, String variableName) {
		return this.execution.getVariableTyped(variableName);
	}

	@Override
	public <T extends TypedValue> T getVariableTyped(String executionId, String variableName, boolean deserializeValue) {
		return this.execution.getVariableTyped(variableName, deserializeValue);
	}

	@Override
	public Object getVariableLocal(String executionId, String variableName) {
		return this.execution.getVariableLocal(variableName);
	}

	@Override
	public <T extends TypedValue> T getVariableLocalTyped(String executionId, String variableName) {
		return this.execution.getVariableLocalTyped(variableName);
	}

	@Override
	public <T extends TypedValue> T getVariableLocalTyped(String executionId, String variableName,
			boolean deserializeValue) {
		return this.execution.getVariableLocalTyped(variableName, deserializeValue);
	}

	@Override
	public Map<String, Object> getVariables(String executionId) {
		return this.execution.getVariables();
	}

	@Override
	public VariableMap getVariablesTyped(String executionId) {
		return this.execution.getVariablesTyped();
	}

	@Override
	public VariableMap getVariablesTyped(String executionId, boolean deserializeValues) {
		return this.execution.getVariablesTyped(deserializeValues);
	}

	@Override
	public Map<String, Object> getVariablesLocal(String executionId) {
		return this.execution.getVariablesLocal();
	}

	@Override
	public VariableMap getVariablesLocalTyped(String executionId) {
		return (VariableMap) this.execution.getVariablesLocalTyped();
	}

	@Override
	public VariableMap getVariablesLocalTyped(String executionId, boolean deserializeValues) {
		return (VariableMap) this.execution.getVariablesLocalTyped(deserializeValues);
	}

	@Override
	public Map<String, Object> getVariables(String executionId, Collection<String> variableNames) {
		Map<String, Object> vars = this.execution.getVariables();
		Map<String, Object> result = new HashMap<>();
		for (String string : variableNames) {
			result.put(string, vars.get(string));
		}
		return result;
	}

	@Override
	public VariableMap getVariablesTyped(String executionId, Collection<String> variableNames, boolean deserializeValues) {
		Map<String, Object> vars = this.execution.getVariablesTyped(deserializeValues);
		Map<String, Object> result = new HashMap<>();
		for (String string : variableNames) {
			result.put(string, vars.get(string));
		}
		return (VariableMap) result;
	}

	@Override
	public Map<String, Object> getVariablesLocal(String executionId, Collection<String> variableNames) {
		Map<String, Object> vars = this.execution.getVariablesLocal();
		Map<String, Object> result = new HashMap<>();
		for (String string : variableNames) {
			result.put(string, vars.get(string));
		}
		return result;
	}

	@Override
	public VariableMap getVariablesLocalTyped(String executionId, Collection<String> variableNames,
			boolean deserializeValues) {
		Map<String, Object> vars = this.execution.getVariablesLocalTyped();
		Map<String, Object> result = new HashMap<>();
		for (String string : variableNames) {
			result.put(string, vars.get(string));
		}
		return (VariableMap) result;
	}

	@Override
	public void removeVariable(String executionId, String variableName) {
		this.execution.removeVariable(variableName);
	}

	@Override
	public void removeVariableLocal(String executionId, String variableName) {
		this.execution.removeVariableLocal(variableName);
	}

	@Override
	public void removeVariables(String executionId, Collection<String> variableNames) {
		this.execution.removeVariables(variableNames);
	}

	@Override
	public void removeVariablesLocal(String executionId, Collection<String> variableNames) {
		this.execution.removeVariablesLocal(variableNames);
	}

	// *********************************************
	// NOT RECOMMENDED
	// *********************************************

	@Override
	public ProcessInstance startProcessInstanceByKey(String processDefinitionKey) {
		throw new NotImplementedExecption();
	}

	@Override
	public ProcessInstance startProcessInstanceByKey(String processDefinitionKey, String businessKey) {
		throw new NotImplementedExecption();
	}

	@Override
	public ProcessInstance startProcessInstanceByKey(String processDefinitionKey, String businessKey,
			String caseInstanceId) {
		throw new NotImplementedExecption();
	}

	@Override
	public ProcessInstance startProcessInstanceByKey(String processDefinitionKey, Map<String, Object> variables) {
		throw new NotImplementedExecption();
	}

	@Override
	public ProcessInstance startProcessInstanceByKey(String processDefinitionKey, String businessKey,
			Map<String, Object> variables) {
		throw new NotImplementedExecption();
	}

	@Override
	public ProcessInstance startProcessInstanceByKey(String processDefinitionKey, String businessKey,
			String caseInstanceId, Map<String, Object> variables) {
		throw new NotImplementedExecption();
	}

	@Override
	public ProcessInstance startProcessInstanceById(String processDefinitionId) {
		throw new NotImplementedExecption();
	}

	@Override
	public ProcessInstance startProcessInstanceById(String processDefinitionId, String businessKey) {
		throw new NotImplementedExecption();
	}

	@Override
	public ProcessInstance startProcessInstanceById(String processDefinitionId, String businessKey,
			String caseInstanceId) {
		throw new NotImplementedExecption();
	}

	@Override
	public ProcessInstance startProcessInstanceById(String processDefinitionId, Map<String, Object> variables) {
		throw new NotImplementedExecption();
	}

	@Override
	public ProcessInstance startProcessInstanceById(String processDefinitionId, String businessKey,
			Map<String, Object> variables) {
		throw new NotImplementedExecption();
	}

	@Override
	public ProcessInstance startProcessInstanceById(String processDefinitionId, String businessKey,
			String caseInstanceId, Map<String, Object> variables) {
		throw new NotImplementedExecption();
	}

	@Override
	public ProcessInstance startProcessInstanceByMessage(String messageName) {
		throw new NotImplementedExecption();
	}

	@Override
	public ProcessInstance startProcessInstanceByMessage(String messageName, String businessKey) {
		throw new NotImplementedExecption();
	}

	@Override
	public ProcessInstance startProcessInstanceByMessage(String messageName, Map<String, Object> processVariables) {
		throw new NotImplementedExecption();
	}

	@Override
	public ProcessInstance startProcessInstanceByMessage(String messageName, String businessKey,
			Map<String, Object> processVariables) {
		throw new NotImplementedExecption();
	}

	@Override
	public ProcessInstance startProcessInstanceByMessageAndProcessDefinitionId(String messageName,
			String processDefinitionId) {
		throw new NotImplementedExecption();
	}

	@Override
	public ProcessInstance startProcessInstanceByMessageAndProcessDefinitionId(String messageName,
			String processDefinitionId, String businessKey) {
		throw new NotImplementedExecption();
	}

	@Override
	public ProcessInstance startProcessInstanceByMessageAndProcessDefinitionId(String messageName,
			String processDefinitionId, Map<String, Object> processVariables) {
		throw new NotImplementedExecption();
	}

	@Override
	public ProcessInstance startProcessInstanceByMessageAndProcessDefinitionId(String messageName,
			String processDefinitionId, String businessKey, Map<String, Object> processVariables) {
		throw new NotImplementedExecption();
	}

	@Override
	public void deleteProcessInstance(String processInstanceId, String deleteReason) {
		throw new NotImplementedExecption();

	}

	@Override
	public Batch deleteProcessInstancesAsync(List<String> processInstanceIds, ProcessInstanceQuery processInstanceQuery,
			String deleteReason) {
		throw new NotImplementedExecption();
	}

	@Override
	public Batch deleteProcessInstancesAsync(List<String> processInstanceIds, ProcessInstanceQuery processInstanceQuery,
			String deleteReason, boolean skipCustomListeners) {
		throw new NotImplementedExecption();
	}

	@Override
	public Batch deleteProcessInstancesAsync(List<String> processInstanceIds, ProcessInstanceQuery processInstanceQuery,
			String deleteReason, boolean skipCustomListeners, boolean skipSubprocesses) {
		throw new NotImplementedExecption();
	}

	@Override
	public Batch deleteProcessInstancesAsync(ProcessInstanceQuery processInstanceQuery, String deleteReason) {
		throw new NotImplementedExecption();
	}

	@Override
	public Batch deleteProcessInstancesAsync(List<String> processInstanceIds, String deleteReason) {
		throw new NotImplementedExecption();
	}

	@Override
	public void deleteProcessInstance(String processInstanceId, String deleteReason, boolean skipCustomListeners) {
		throw new NotImplementedExecption();
	}

	@Override
	public void deleteProcessInstance(String processInstanceId, String deleteReason, boolean skipCustomListeners,
			boolean externallyTerminated) {
		throw new NotImplementedExecption();
	}

	@Override
	public void deleteProcessInstances(List<String> processInstanceIds, String deleteReason,
			boolean skipCustomListeners, boolean externallyTerminated) {
		throw new NotImplementedExecption();
	}

	@Override
	public void deleteProcessInstances(List<String> processInstanceIds, String deleteReason,
			boolean skipCustomListeners, boolean externallyTerminated, boolean skipSubprocesses) {
		throw new NotImplementedExecption();
	}

	@Override
	public void deleteProcessInstance(String processInstanceId, String deleteReason, boolean skipCustomListeners,
			boolean externallyTerminated, boolean skipIoMappings) {
		throw new NotImplementedExecption();
	}

	@Override
	public void deleteProcessInstance(String processInstanceId, String deleteReason, boolean skipCustomListeners,
			boolean externallyTerminated, boolean skipIoMappings, boolean skipSubprocesses) {
		throw new NotImplementedExecption();
	}

	@Override
	public List<String> getActiveActivityIds(String executionId) {
		throw new NotImplementedExecption();
	}

	@Override
	public ActivityInstance getActivityInstance(String processInstanceId) {
		throw new NotImplementedExecption();
	}

	@Override
	public void signal(String executionId) {
		throw new NotImplementedExecption();
	}

	@Override
	public void signal(String executionId, String signalName, Object signalData, Map<String, Object> processVariables) {
		throw new NotImplementedExecption();
	}

	@Override
	public void signal(String executionId, Map<String, Object> processVariables) {
		throw new NotImplementedExecption();
	}

	@Override
	public ExecutionQuery createExecutionQuery() {
		throw new NotImplementedExecption();
	}

	@Override
	public NativeExecutionQuery createNativeExecutionQuery() {
		throw new NotImplementedExecption();
	}

	@Override
	public ProcessInstanceQuery createProcessInstanceQuery() {
		throw new NotImplementedExecption();
	}

	@Override
	public NativeProcessInstanceQuery createNativeProcessInstanceQuery() {
		throw new NotImplementedExecption();
	}

	@Override
	public IncidentQuery createIncidentQuery() {
		throw new NotImplementedExecption();
	}

	@Override
	public EventSubscriptionQuery createEventSubscriptionQuery() {
		throw new NotImplementedExecption();
	}

	@Override
	public VariableInstanceQuery createVariableInstanceQuery() {
		throw new NotImplementedExecption();
	}

	@Override
	public void suspendProcessInstanceById(String processInstanceId) {
		throw new NotImplementedExecption();

	}

	@Override
	public void suspendProcessInstanceByProcessDefinitionId(String processDefinitionId) {
		throw new NotImplementedExecption();

	}

	@Override
	public void suspendProcessInstanceByProcessDefinitionKey(String processDefinitionKey) {
		throw new NotImplementedExecption();

	}

	@Override
	public void activateProcessInstanceById(String processInstanceId) {
		throw new NotImplementedExecption();

	}

	@Override
	public void activateProcessInstanceByProcessDefinitionId(String processDefinitionId) {
		throw new NotImplementedExecption();

	}

	@Override
	public void activateProcessInstanceByProcessDefinitionKey(String processDefinitionKey) {
		throw new NotImplementedExecption();

	}

	@Override
	public UpdateProcessInstanceSuspensionStateSelectBuilder updateProcessInstanceSuspensionState() {
		throw new NotImplementedExecption();
	}

	@Override
	public void signalEventReceived(String signalName) {
		throw new NotImplementedExecption();

	}

	@Override
	public void signalEventReceived(String signalName, Map<String, Object> processVariables) {
		throw new NotImplementedExecption();

	}

	@Override
	public void signalEventReceived(String signalName, String executionId) {
		throw new NotImplementedExecption();

	}

	@Override
	public void signalEventReceived(String signalName, String executionId, Map<String, Object> processVariables) {
		throw new NotImplementedExecption();

	}

	@Override
	public SignalEventReceivedBuilder createSignalEvent(String signalName) {
		throw new NotImplementedExecption();
	}

	@Override
	public void messageEventReceived(String messageName, String executionId) {
		throw new NotImplementedExecption();

	}

	@Override
	public void messageEventReceived(String messageName, String executionId, Map<String, Object> processVariables) {
		throw new NotImplementedExecption();

	}

	@Override
	public MessageCorrelationBuilder createMessageCorrelation(String messageName) {
		throw new NotImplementedExecption();
	}

	@Override
	public void correlateMessage(String messageName) {
		throw new NotImplementedExecption();

	}

	@Override
	public void correlateMessage(String messageName, String businessKey) {
		throw new NotImplementedExecption();

	}

	@Override
	public void correlateMessage(String messageName, Map<String, Object> correlationKeys) {
		throw new NotImplementedExecption();

	}

	@Override
	public void correlateMessage(String messageName, String businessKey, Map<String, Object> processVariables) {
		throw new NotImplementedExecption();

	}

	@Override
	public void correlateMessage(String messageName, Map<String, Object> correlationKeys,
			Map<String, Object> processVariables) {
		throw new NotImplementedExecption();

	}

	@Override
	public void correlateMessage(String messageName, String businessKey, Map<String, Object> correlationKeys,
			Map<String, Object> processVariables) {
		throw new NotImplementedExecption();
	}

	@Override
	public ProcessInstanceModificationBuilder createProcessInstanceModification(String processInstanceId) {
		throw new NotImplementedExecption();
	}

	@Override
	public ProcessInstantiationBuilder createProcessInstanceById(String processDefinitionId) {
		throw new NotImplementedExecption();
	}

	@Override
	public ProcessInstantiationBuilder createProcessInstanceByKey(String processDefinitionKey) {
		throw new NotImplementedExecption();
	}

	@Override
	public MigrationPlanBuilder createMigrationPlan(String sourceProcessDefinitionId,
			String targetProcessDefinitionId) {
		throw new NotImplementedExecption();
	}

	@Override
	public MigrationPlanExecutionBuilder newMigration(MigrationPlan migrationPlan) {
		throw new NotImplementedExecption();
	}

	@Override
	public ModificationBuilder createModification(String processDefinitionId) {
		throw new NotImplementedExecption();
	}

	@Override
	public RestartProcessInstanceBuilder restartProcessInstances(String processDefinitionId) {
		throw new NotImplementedExecption();
	}

	@Override
	public Incident createIncident(String incidentType, String executionId, String configuration) {
		throw new NotImplementedExecption();
	}

	@Override
	public Incident createIncident(String incidentType, String executionId, String configuration, String message) {
		throw new NotImplementedExecption();
	}

	@Override
	public void resolveIncident(String incidentId) {
		throw new NotImplementedExecption();

	}

}
