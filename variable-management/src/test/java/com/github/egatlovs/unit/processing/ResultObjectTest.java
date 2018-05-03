package com.github.egatlovs.unit.processing;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.github.egatlovs.mock.ResultObjectMockWithoutStoreFields;
import com.github.egatlovs.variablemanager.exceptions.ResultObjectException;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.github.egatlovs.mock.ResultObjectMock;
import com.github.egatlovs.variablemanager.processing.ResultObject;

public class ResultObjectTest {

	@Test
	public void Should_Create_Object_From_Variables() {
			ResultObject resultObject = new ResultObject();
			Map<String, Object> variables = new HashMap<>();
			variables.put("someString", "var1");
			variables.put("fieldPrefix_fieldName", "var2");
			variables.put("myDecimal", BigDecimal.ZERO);
			ResultObjectMock result = resultObject.getValue(ResultObjectMock.class, variables);
			Assertions.assertThat(result.getAnnotated()).isEqualTo("var2");
			Assertions.assertThat(result.getSomeString()).isEqualTo("var1");
			Assertions.assertThat(result.getDecimal()).isEqualTo(BigDecimal.ZERO);
			Assertions.assertThat(result.getIgnoredField()).isNull();
	}

	@Test
	public void Should_Create_Object() {
		ResultObject resultObject = new ResultObject();
		Map<String, Object> variables = new HashMap<>();
		variables.put("my_resultObject", new ResultObjectMockWithoutStoreFields());
		ResultObjectMockWithoutStoreFields result = resultObject.getValue(ResultObjectMockWithoutStoreFields.class, variables);
		Assertions.assertThat(result.getAnnotated()).isEqualTo("annotatedString");
		Assertions.assertThat(result.getSomeString()).isEqualTo("string");
		Assertions.assertThat(result.getDecimal()).isEqualTo(BigDecimal.ONE);
		Assertions.assertThat(result.getIgnoredField()).isNull();
	}

	@Test
	public void Should_Throw_Exception_On_Wrong_Varibales_Object() {
		ResultObject resultObject = new ResultObject();
		Map<String, Object> variables = new HashMap<>();
		variables.put("my_resultObject", new ResultObjectMockWithoutStoreFields());
		variables.put("someVar", null);
		try {
			ResultObjectMockWithoutStoreFields result = resultObject.getValue(ResultObjectMockWithoutStoreFields.class, variables);
			Assertions.fail("should throw exception");
		} catch (ResultObjectException e){
			return;
		}
		Assertions.fail("exception should've been catched");
	}

}
