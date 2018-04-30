package com.github.egatlovs.variablemanager.producers;

import javax.enterprise.inject.Produces;

import org.camunda.bpm.engine.context.DelegateExecutionContext;
import org.camunda.bpm.engine.delegate.DelegateExecution;

/**
 * <b>ExecutionProducer</b></br>
 * </br>
 * This class provides the current DelegateExecution if present otherwise null
 * is returned.
 * 
 * @author egatlovs
 */
public class ExecutionProducer {

	/**
	 * Produces a DelegateExecution.
	 * 
	 * @return - The current DelegateExecution or null if no execution is present
	 */
	@Produces
	public DelegateExecution getDelegateExecution() {
		return DelegateExecutionContext.getCurrentDelegationExecution();
	}

}
