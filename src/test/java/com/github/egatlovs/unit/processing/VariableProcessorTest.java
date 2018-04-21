package com.github.egatlovs.unit.processing;

import java.math.BigDecimal;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.junit.Test;

import com.github.egatlovs.mock.VariableProcessorMockJsonAnnotated;
import com.github.egatlovs.mock.VariableProcessorMockObject;
import com.github.egatlovs.mock.VariableProcessorMockObjectJson;
import com.github.egatlovs.mock.VariablesProcessorMock;
import com.github.egatlovs.variablemanager.processing.VariableProcessor;

public class VariableProcessorTest {

	@Test
	public void Should_Create_Right_Variables_From_Object() {
		VariableProcessor processor = new VariableProcessor();
		Map<String, Object> variables = processor.process(new VariablesProcessorMock());

		Assertions.assertThat(variables).containsEntry("someString", "string");
		Assertions.assertThat(variables).containsEntry("myDecimal", BigDecimal.ONE);
		Assertions.assertThat(variables).containsEntry("fieldPrefix_fieldName", "annotatedString");
	}

	@Test
	public void Should_Create_Right_Variables_From_Object_Json_Annotated() {
		VariableProcessor processor = new VariableProcessor();
		Map<String, Object> variables = processor.process(new VariableProcessorMockJsonAnnotated());

		Assertions.assertThat(variables).containsKeys("someString", "myDecimal", "fieldPrefix_fieldName");
		variables.values().forEach(value -> Assertions.assertThat(((ObjectValue) value).getValue()).isIn("string",
				"annotatedString", BigDecimal.ONE));
	}

	@Test
	public void Should_Create_Right_Object_From_Object_Json_Annotated() {
		VariableProcessor processor = new VariableProcessor();
		VariableProcessorMockObjectJson mock = new VariableProcessorMockObjectJson();
		Map<String, Object> variables = processor.process(mock);

		Assertions.assertThat(variables).containsKeys("VariableProcessorMockObjectJson");
		Assertions.assertThat(((ObjectValue) variables.get("VariableProcessorMockObjectJson")).getValue())
				.isEqualTo(mock);
	}

	@Test
	public void Should_Create_Right_Object_From_Object() {
		VariableProcessor processor = new VariableProcessor();
		Map<String, Object> variables = processor.process(new VariableProcessorMockObject());

		Assertions.assertThat(variables).containsKeys("VariableProcessorMockObject");
		Assertions
				.assertThat(((VariableProcessorMockObject) variables.get("VariableProcessorMockObject")).getAnnotated())
				.isEqualTo("annotatedString");
		Assertions
				.assertThat(
						((VariableProcessorMockObject) variables.get("VariableProcessorMockObject")).getSomeString())
				.isEqualTo("string");
		Assertions.assertThat(((VariableProcessorMockObject) variables.get("VariableProcessorMockObject")).getDecimal())
				.isEqualTo(BigDecimal.ONE);
		Assertions
				.assertThat(
						((VariableProcessorMockObject) variables.get("VariableProcessorMockObject")).getIgnoredField())
				.isNull();
	}

}
