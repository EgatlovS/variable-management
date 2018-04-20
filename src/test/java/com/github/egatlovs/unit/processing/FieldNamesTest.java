package com.github.egatlovs.unit.processing;

import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.github.egatlovs.mock.ExecutionAnnotationMock;
import com.github.egatlovs.mock.FieldNamesMock;
import com.github.egatlovs.variablemanager.processing.FieldNames;

public class FieldNamesTest {

	@Test
	public void Should_Return_DeclaredFieldNames() {
		FieldNames fieldNames = new FieldNames();
		Set<String> names = fieldNames.get(FieldNamesMock.class);
		Assertions.assertThat(names).contains("notAnnotated", "name", "prefix_name");
	}

	@Test
	public void Should_Return_DeclaredObjectName() {
		FieldNames fieldNames = new FieldNames();
		Set<String> names = fieldNames.get(ExecutionAnnotationMock.class);
		Assertions.assertThat(names).contains("ExecutionAnnotationMock");
	}

}
