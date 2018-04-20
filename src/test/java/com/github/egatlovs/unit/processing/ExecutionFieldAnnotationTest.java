package com.github.egatlovs.unit.processing;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.github.egatlovs.mock.ExecutionFieldAnnotationMockWithNameAndPrefix;
import com.github.egatlovs.mock.ExecutionFieldAnnotationMockWithoutAnnotation;
import com.github.egatlovs.mock.ExecutionFieldAnnotationMockWithoutPrefixAnnotation;
import com.github.egatlovs.variablemanager.processing.FieldName;

public class ExecutionFieldAnnotationTest {

	@Test
	public void Should_Return_FieldName_When_No_Annotation_Is_Present() {
		FieldName fieldName = new FieldName();
		Assertions.assertThat(fieldName.getFrom(new ExecutionFieldAnnotationMockWithoutAnnotation()))
				.isEqualTo("ExecutionFieldAnnotationMockWithoutAnnotation");
	}

	@Test
	public void Should_Return_Declared_Prefix_And_Name_When_Annotation_Is_Present() {
		FieldName fieldName = new FieldName();
		Assertions.assertThat(fieldName.getFrom(new ExecutionFieldAnnotationMockWithNameAndPrefix()))
				.isEqualTo("prefix_name");
	}

	@Test
	public void Should_Return_Declared_Name_When_Annotation_Is_Present_But_No_Prefix_Is_Applied() {
		FieldName fieldName = new FieldName();
		Assertions.assertThat(fieldName.getFrom(new ExecutionFieldAnnotationMockWithoutPrefixAnnotation()))
				.isEqualTo("name");
	}

}
