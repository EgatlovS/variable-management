package com.github.egatlovs.variablemanager.processing;

import java.lang.reflect.Field;

import com.github.egatlovs.variablemanager.annotations.Execution;
import com.github.egatlovs.variablemanager.annotations.StoreStrategies;

/**
 * <b>ExecutionAnnotation</b></br>
 * </br>
 * ExecutionAnnotation extracts the Annotation {@code @Execution} from a given
 * Objects and provides access to the Annotations Fields.
 * 
 * @author egatlovs
 */
public class ExecutionAnnotation {

	private final Execution execution;

	/**
	 * Constructor taking an Object and extracting the Annotation
	 * {@code @Execution}. The constructor is aware that Class and Field need to be
	 * accessed differently to retrieve the Annotation.
	 * 
	 * @param o
	 *            -The object where the annotation should be extracted from
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ExecutionAnnotation(Object o) {
		if (o instanceof Class) {
			this.execution = (Execution) ((Class) o).getAnnotation(Execution.class);
		} else if (o instanceof Field) {
			this.execution = ((Field) o).getAnnotation(Execution.class);
		} else {
			this.execution = o.getClass().getAnnotation(Execution.class);
		}
	}

	/**
	 * Constructor retrieving the annotation itself
	 * 
	 * @param execution
	 *            - The Annotation {@code Execution} to be used
	 */
	public ExecutionAnnotation(Execution execution) {
		this.execution = execution;
	}

	/**
	 * Gets the storeStrategy of the wrapped Execution Annotation
	 * 
	 * @return The storeStrategy annotated or OBJECT if execution is null
	 */
	public StoreStrategies getStoreStrategy() {
		if (execution == null) {
			return StoreStrategies.OBJECT;
		}
		return execution.storeStrategy();
	}

	/**
	 * Provides the storeFields variable of the wrapped Execution Annotation
	 * 
	 * @return The storeFields specified or true if execution is null
	 */
	public boolean isStoreFields() {
		if (execution == null) {
			return true;
		}
		return execution.storeFields();
	}

}
