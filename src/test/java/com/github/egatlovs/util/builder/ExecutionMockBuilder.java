package com.github.egatlovs.util.builder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.impl.pvm.runtime.ExecutionImpl;

import com.github.egatlovs.util.exceptions.PropertiesNotFoundException;

public class ExecutionMockBuilder {

	public static DelegateExecution build() {
		return new ExecutionImpl();
	}

	public static DelegateExecution build(Map<String, ? extends Object> variables) {
		DelegateExecution execution = new ExecutionImpl();
		execution.setVariables(variables);
		return execution;
	}

	public static DelegateExecution build(String pathToProperties) throws PropertiesNotFoundException {
		DelegateExecution execution = new ExecutionImpl();
		Properties props = getProperties(pathToProperties);
		Map<String, Object> variables = new HashMap<>();
		for (final String name : props.stringPropertyNames()) {
			variables.put(name, props.getProperty(name));
		}
		execution.setVariables(variables);
		return execution;
	}

	private static Properties getProperties(String pathToProperties) throws PropertiesNotFoundException {
		InputStream propertyFile = ExecutionMockBuilder.class.getClassLoader().getResourceAsStream(pathToProperties);
		Properties properties = new Properties();
		try {
			properties.load(propertyFile);
		} catch (IOException e) {
			throw new PropertiesNotFoundException(
					"An error occurred initializing execution from properties file. Following Path was passed: "
							+ pathToProperties);
		}
		return properties;
	}

}
