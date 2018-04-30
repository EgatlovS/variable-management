package com.github.egatlovs.util.mocks;

import java.util.Calendar;
import java.util.Date;

import org.camunda.bpm.engine.task.DelegationState;
import org.camunda.bpm.engine.task.Task;

public class TaskMock implements Task {

	private String taskInstanceId;
	private String name;
	private String description;
	private int priority;
	private String owner;
	private String assignee;
	private DelegationState delegationState;
	private String processInstanceId;
	private String executionId;
	private String processDefinitionId;
	private String caseInstanceId;
	private String caseExecutionId;
	private Date createTime;
	private String taskDefinitionKey;
	private Date dueDate;
	private Date followUpDate;
	private String parentTaskId;
	private boolean isSuspended;
	private String formKey;
	private String tenantId;
	private String caseDefinitionId;

	@Override
	public String getId() {
		return taskInstanceId;
	}

	public void setId(String taskId) {
		this.taskInstanceId = taskId;
	}

	public String getCaseDefinitionId() {
		return caseDefinitionId;
	}

	public void setCaseDefinitionId(String caseDefinitionId) {
		this.caseDefinitionId = caseDefinitionId;
	}

	@Override
	public void delegate(String userId) {
		this.delegationState = DelegationState.PENDING;
		this.owner = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public DelegationState getDelegationState() {
		return delegationState;
	}

	public void setDelegationState(DelegationState delegationState) {
		this.delegationState = delegationState;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getExecutionId() {
		return executionId;
	}

	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public String getCaseInstanceId() {
		return caseInstanceId;
	}

	public void setCaseInstanceId(String caseInstanceId) {
		this.caseInstanceId = caseInstanceId;
	}

	public String getCaseExecutionId() {
		return caseExecutionId;
	}

	public void setCaseExecutionId(String caseExecutionId) {
		this.caseExecutionId = caseExecutionId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getTaskDefinitionKey() {
		return taskDefinitionKey;
	}

	public void setTaskDefinitionKey(String taskDefinitionKey) {
		this.taskDefinitionKey = taskDefinitionKey;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getFollowUpDate() {
		return followUpDate;
	}

	public void setFollowUpDate(Date followUpDate) {
		this.followUpDate = followUpDate;
	}

	public String getParentTaskId() {
		return parentTaskId;
	}

	public void setParentTaskId(String parentTaskId) {
		this.parentTaskId = parentTaskId;
	}

	public boolean isSuspended() {
		return isSuspended;
	}

	public void setSuspended(boolean isSuspended) {
		this.isSuspended = isSuspended;
	}

	public String getFormKey() {
		return formKey;
	}

	public void setFormKey(String formKey) {
		this.formKey = formKey;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public static final class Builder {

		public static Task buildDefault() {
			return build("taskinstanceid");
		}

		public static Task build(String taskId) {
			TaskMock task = new TaskMock();
			task.taskInstanceId = taskId;
			task.name = "Task Name";
			task.description = "thats a default task";
			task.priority = 0;
			task.owner = "Owner Man";
			task.assignee = "assignee";
			task.delegationState = DelegationState.PENDING;
			task.processInstanceId = "processinstanceid";
			task.executionId = "executionid";
			task.processDefinitionId = "processdefinitionid";
			task.caseInstanceId = "caseinstanceid";
			task.caseExecutionId = "caseexecutionid";
			task.caseDefinitionId = "casedefinitionid";
			task.createTime = Calendar.getInstance().getTime();
			task.taskDefinitionKey = "taskdefinitionkey";
			Calendar dueDate = Calendar.getInstance();
			dueDate.roll(Calendar.DAY_OF_MONTH, true);
			task.dueDate = dueDate.getTime();
			Calendar followUp = Calendar.getInstance();
			followUp.roll(Calendar.DAY_OF_MONTH, true);
			task.followUpDate = followUp.getTime();
			task.parentTaskId = "parenttaskid";
			task.isSuspended = false;
			task.formKey = "formkey";
			task.tenantId = "tenantid";
			return task;
		}

	}

}
