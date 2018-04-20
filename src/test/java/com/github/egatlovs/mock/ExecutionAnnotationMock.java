package com.github.egatlovs.mock;

import com.github.egatlovs.variablemanager.StoreStrategies;
import com.github.egatlovs.variablemanager.annotations.Execution;

@Execution(storeFields = false, storeStartegy = StoreStrategies.JSON)
public class ExecutionAnnotationMock {

}
