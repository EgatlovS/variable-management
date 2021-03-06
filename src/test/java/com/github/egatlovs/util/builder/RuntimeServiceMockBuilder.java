package com.github.egatlovs.util.builder;

import com.github.egatlovs.util.exceptions.PropertiesNotFoundException;
import com.github.egatlovs.util.mocks.RuntimeServiceMock;

import java.util.Map;

public class RuntimeServiceMockBuilder {

    public static RuntimeServiceMock build() {
        return new RuntimeServiceMock(ExecutionMockBuilder.build());
    }

    public static RuntimeServiceMock build(Map<String, ? extends Object> variables) {
        return new RuntimeServiceMock(ExecutionMockBuilder.build(variables));
    }

    public static RuntimeServiceMock build(String pathToProperties) throws PropertiesNotFoundException {
        return new RuntimeServiceMock(ExecutionMockBuilder.build(pathToProperties));
    }

}
