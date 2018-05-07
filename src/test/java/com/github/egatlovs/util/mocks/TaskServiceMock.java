package com.github.egatlovs.util.mocks;

import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.task.Attachment;
import org.camunda.bpm.engine.task.Comment;
import org.camunda.bpm.engine.task.DelegationState;
import org.camunda.bpm.engine.task.Event;
import org.camunda.bpm.engine.task.IdentityLink;
import org.camunda.bpm.engine.task.NativeTaskQuery;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.task.TaskQuery;
import org.camunda.bpm.engine.task.TaskReport;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.value.TypedValue;

@SuppressWarnings({"deprecation", "TypeParameterExplicitlyExtendsObject"})
public class TaskServiceMock implements TaskService {

	private TaskMock task;
	private final DelegateExecution execution;

	public TaskServiceMock(DelegateExecution execution) {
		this.newTask();
		this.execution = execution;
	}

	@Override
	public void setVariable(String taskId, String variableName, Object value) {
		this.execution.setVariable(variableName, value);
	}

	@Override
	public void setVariables(String taskId, Map<String, ? extends Object> execution) {
		this.execution.setVariables(execution);
	}

	@Override
	public void setVariableLocal(String taskId, String variableName, Object value) {
		this.execution.setVariableLocal(variableName, value);
	}

	@Override
	public void setVariablesLocal(String taskId, Map<String, ? extends Object> execution) {
		this.execution.setVariablesLocal(execution);
	}

	@Override
	public Object getVariable(String taskId, String variableName) {
		return this.execution.getVariable(variableName);
	}

	@Override
	public <T extends TypedValue> T getVariableTyped(String taskId, String variableName) {
		return this.execution.getVariableTyped(variableName);
	}

	@Override
	public <T extends TypedValue> T getVariableTyped(String taskId, String variableName, boolean deserializeValue) {
		return this.execution.getVariableTyped(variableName, deserializeValue);
	}

	@Override
	public Object getVariableLocal(String taskId, String variableName) {
		return this.execution.getVariableLocal(variableName);
	}

	@Override
	public <T extends TypedValue> T getVariableLocalTyped(String taskId, String variableName) {
		return this.execution.getVariableLocalTyped(variableName);
	}

	@Override
	public <T extends TypedValue> T getVariableLocalTyped(String taskId, String variableName,
			boolean deserializeValue) {
		return this.execution.getVariableLocalTyped(variableName, deserializeValue);
	}

	@Override
	public Map<String, Object> getVariables(String taskId) {
		return this.execution.getVariables();
	}

	@Override
	public VariableMap getVariablesTyped(String taskId) {
		return this.execution.getVariablesTyped();
	}

	@Override
	public VariableMap getVariablesTyped(String taskId, boolean deserializeValues) {
		return this.execution.getVariablesTyped(deserializeValues);
	}

	@Override
	public Map<String, Object> getVariablesLocal(String taskId) {
		return this.execution.getVariablesLocal();
	}

	@Override
	public VariableMap getVariablesLocalTyped(String taskId) {
		return this.execution.getVariablesLocalTyped();
	}

	@Override
	public VariableMap getVariablesLocalTyped(String taskId, boolean deserializeValues) {
		return this.execution.getVariablesLocalTyped(deserializeValues);
	}

	@Override
	public Map<String, Object> getVariables(String taskId, Collection<String> variableNames) {
		Map<String, Object> vars = this.execution.getVariables();
		Map<String, Object> result = new HashMap<>();
		for (String string : variableNames) {
			result.put(string, vars.get(string));
		}
		return result;
	}

	@Override
	public VariableMap getVariablesTyped(String taskId, Collection<String> variableNames, boolean deserializeValues) {
		Map<String, Object> vars = this.execution.getVariablesTyped(deserializeValues);
		Map<String, Object> result = new HashMap<>();
		for (String string : variableNames) {
			result.put(string, vars.get(string));
		}
		return (VariableMap) result;
	}

	@Override
	public Map<String, Object> getVariablesLocal(String taskId, Collection<String> variableNames) {
		Map<String, Object> vars = this.execution.getVariablesLocal();
		Map<String, Object> result = new HashMap<>();
		for (String string : variableNames) {
			result.put(string, vars.get(string));
		}
		return result;
	}

	@Override
	public VariableMap getVariablesLocalTyped(String taskId, Collection<String> variableNames,
			boolean deserializeValues) {
		Map<String, Object> vars = this.execution.getVariablesLocalTyped();
		Map<String, Object> result = new HashMap<>();
		for (String string : variableNames) {
			result.put(string, vars.get(string));
		}
		return (VariableMap) result;
	}

	@Override
	public void removeVariable(String taskId, String variableName) {
		this.execution.removeVariable(variableName);
	}

	@Override
	public void removeVariableLocal(String taskId, String variableName) {
		this.execution.removeVariableLocal(variableName);
	}

	@Override
	public void removeVariables(String taskId, Collection<String> variableNames) {
		this.execution.removeVariables(variableNames);
	}

	@Override
	public void removeVariablesLocal(String taskId, Collection<String> variableNames) {
		this.execution.removeVariablesLocal(variableNames);
	}

	// *********************************************
	// NOT RECOMMENDED
	// *********************************************

	@SuppressWarnings("DeprecatedIsStillUsed")
	@Deprecated
	@Override
	public Task newTask() {
		return TaskMock.Builder.buildDefault();
	}

	@Deprecated
	@Override
	public Task newTask(String taskId) {
		return TaskMock.Builder.build(taskId);
	}

	@Deprecated
	@Override
	public void saveTask(Task task) {
		this.task = (TaskMock) task;
	}

	@Deprecated
	@Override
	public void deleteTask(String taskId) {
		this.task = null;
	}

	@Deprecated
	@Override
	public void deleteTasks(Collection<String> taskIds) {
		this.task = null;
	}

	@Deprecated
	@Override
	public void deleteTask(String taskId, boolean cascade) {
		this.task = null;
	}

	@Deprecated
	@Override
	public void deleteTasks(Collection<String> taskIds, boolean cascade) {
		this.task = null;
	}

	@Deprecated
	@Override
	public void deleteTask(String taskId, String deleteReason) {
		this.task = null;
	}

	@Deprecated
	@Override
	public void deleteTasks(Collection<String> taskIds, String deleteReason) {
		this.task = null;
	}

	@Deprecated
	@Override
	public void claim(String taskId, String userId) {
		this.task.setAssignee(userId);
	}

	@Deprecated
	@Override
	public void complete(String taskId) {
		this.task = null;
	}

	@Deprecated
	@Override
	public void delegateTask(String taskId, String userId) {
		this.task.delegate(userId);
	}

	@Deprecated
	@Override
	public void resolveTask(String taskId) {
		this.task.setDelegationState(DelegationState.RESOLVED);
	}

	@Deprecated
	@Override
	public void resolveTask(String taskId, Map<String, Object> execution) {
		this.task.setDelegationState(DelegationState.RESOLVED);
		this.execution.setVariables(execution);
	}

	@Deprecated
	@Override
	public void complete(String taskId, Map<String, Object> execution) {
		this.task = null;
	}

	@Deprecated
	@Override
	public void setAssignee(String taskId, String userId) {
		this.task.setAssignee(userId);
	}

	@Deprecated
	@Override
	public void setOwner(String taskId, String userId) {
		this.task.setOwner(userId);
	}

	// ********************************************************//
	// NOT IMPLEMENTED
	// ********************************************************//
	@Deprecated
	@Override
	public List<IdentityLink> getIdentityLinksForTask(String taskId) {
		return null;
	}

	@Deprecated
	@Override
	public void addCandidateUser(String taskId, String userId) {
		// don't implement
	}

	@Deprecated
	@Override
	public void addCandidateGroup(String taskId, String groupId) {
		// don't implement
	}

	@Deprecated
	@Override
	public void addUserIdentityLink(String taskId, String userId, String identityLinkType) {
		// don't implement
	}

	@Deprecated
	@Override
	public void addGroupIdentityLink(String taskId, String groupId, String identityLinkType) {
		// don't implement
	}

	@Deprecated
	@Override
	public void deleteCandidateUser(String taskId, String userId) {
		// don't implement
	}

	@Deprecated
	@Override
	public void deleteCandidateGroup(String taskId, String groupId) {
		// don't implement
	}

	@Deprecated
	@Override
	public void deleteUserIdentityLink(String taskId, String userId, String identityLinkType) {
		/// don't implement
	}

	@Deprecated
	@Override
	public void deleteGroupIdentityLink(String taskId, String groupId, String identityLinkType) {
		// don't implement
	}

	@Deprecated
	@Override
	public void setPriority(String taskId, int priority) {
		this.task.setPriority(priority);
	}

	@Deprecated
	@Override
	public TaskQuery createTaskQuery() {
		// don't implement
		return null;
	}

	@Deprecated
	@Override
	public NativeTaskQuery createNativeTaskQuery() {
		// don't implement
		return null;
	}

	@Deprecated
	@Override
	public void addComment(String taskId, String processInstanceId, String message) {
		// don't implement
	}

	@Deprecated
	@Override
	public Comment createComment(String taskId, String processInstanceId, String message) {
		// don't implement
		return null;
	}

	@Deprecated
	@Override
	public List<Comment> getTaskComments(String taskId) {
		// don't implement
		return null;
	}

	@Deprecated
	@Override
	public Comment getTaskComment(String taskId, String commentId) {
		// don't implement
		return null;
	}

	@Deprecated
	@Override
	public List<Event> getTaskEvents(String taskId) {
		// don't implement
		return null;
	}

	@Deprecated
	@Override
	public List<Comment> getProcessInstanceComments(String processInstanceId) {
		// don't implement
		return null;
	}

	@Deprecated
	@Override
	public Attachment createAttachment(String attachmentType, String taskId, String processInstanceId,
			String attachmentName, String attachmentDescription, InputStream content) {
		// don't implement
		return null;
	}

	@Deprecated
	@Override
	public Attachment createAttachment(String attachmentType, String taskId, String processInstanceId,
			String attachmentName, String attachmentDescription, String url) {
		// don't implement
		return null;
	}

	@Deprecated
	@Override
	public void saveAttachment(Attachment attachment) {
		// don't implement
	}

	@Deprecated
	@Override
	public Attachment getAttachment(String attachmentId) {
		// don't implement
		return null;
	}

	@Deprecated
	@Override
	public Attachment getTaskAttachment(String taskId, String attachmentId) {
		// don't implement
		return null;
	}

	@Deprecated
	@Override
	public InputStream getAttachmentContent(String attachmentId) {
		// don't implement
		return null;
	}

	@Deprecated
	@Override
	public InputStream getTaskAttachmentContent(String taskId, String attachmentId) {
		// don't implement
		return null;
	}

	@Deprecated
	@Override
	public List<Attachment> getTaskAttachments(String taskId) {
		// don't implement
		return null;
	}

	@Deprecated
	@Override
	public List<Attachment> getProcessInstanceAttachments(String processInstanceId) {
		// don't implement
		return null;
	}

	@Deprecated
	@Override
	public void deleteAttachment(String attachmentId) {
		// don't implement
	}

	@Deprecated
	@Override
	public void deleteTaskAttachment(String taskId, String attachmentId) {
		// don't implement
	}

	@Deprecated
	@Override
	public List<Task> getSubTasks(String parentTaskId) {
		// don't implement
		return null;
	}

	@Deprecated
	@Override
	public TaskReport createTaskReport() {
		// don't implement
		return null;
	}

}
