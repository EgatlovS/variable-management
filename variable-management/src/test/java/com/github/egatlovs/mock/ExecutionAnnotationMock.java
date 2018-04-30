package com.github.egatlovs.mock;

import com.github.egatlovs.variablemanager.annotations.Execution;
import com.github.egatlovs.variablemanager.annotations.StoreStrategies;

@Execution(storeFields = false, storeStrategy = StoreStrategies.JSON)
public class ExecutionAnnotationMock {

}
