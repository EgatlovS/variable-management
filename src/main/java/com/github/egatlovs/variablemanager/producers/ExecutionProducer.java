package com.github.egatlovs.variablemanager.producers;

import javax.enterprise.inject.Produces;

import org.camunda.bpm.engine.context.DelegateExecutionContext;
import org.camunda.bpm.engine.delegate.DelegateExecution;

public class ExecutionProducer {

	@Produces
	public DelegateExecution getDelegateExecution() {
		return DelegateExecutionContext.getCurrentDelegationExecution();
	}

}
