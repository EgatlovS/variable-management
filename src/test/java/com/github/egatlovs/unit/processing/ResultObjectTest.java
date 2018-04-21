package com.github.egatlovs.unit.processing;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

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

}
