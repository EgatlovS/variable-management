package com.github.egatlovs.util.builder;

import java.util.Map;

import com.github.egatlovs.util.exceptions.PropertiesNotFoundException;
import com.github.egatlovs.util.mocks.TaskServiceMock;

public class TaskServiceMockBuilder {

	public static TaskServiceMock build() {
		return new TaskServiceMock(ExecutionMockBuilder.build());
	}

	public static TaskServiceMock build(Map<String, ? extends Object> variables) {
		return new TaskServiceMock(ExecutionMockBuilder.build(variables));
	}

	public static TaskServiceMock build(String pathToProperties) throws PropertiesNotFoundException {
		return new TaskServiceMock(ExecutionMockBuilder.build(pathToProperties));
	}

}
