package com.github.egatlovs.unit.processing;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.github.egatlovs.mock.ExecutionFieldAnnotationMockWithNameAndPrefix;
import com.github.egatlovs.mock.ExecutionFieldAnnotationMockWithoutAnnotation;
import com.github.egatlovs.mock.ExecutionFieldAnnotationMockWithoutPrefixAnnotation;
import com.github.egatlovs.variablemanager.processing.ExecutionFieldAnnotation;

public class ExecutionFieldAnnotationTest {

	@Test
	public void Should_Return_FieldName_When_No_Annotation_Is_Present() {
		ExecutionFieldAnnotation annotation = new ExecutionFieldAnnotation(
				new ExecutionFieldAnnotationMockWithoutAnnotation());
		Assertions.assertThat(annotation.getName()).isEqualTo("ExecutionFieldAnnotationMockWithoutAnnotation");
	}

	@Test
	public void Should_Return_Declared_Prefix_And_Name_When_Annotation_Is_Present() {
		ExecutionFieldAnnotation annotation = new ExecutionFieldAnnotation(
				new ExecutionFieldAnnotationMockWithNameAndPrefix());
		Assertions.assertThat(annotation.getName()).isEqualTo("prefix_name");
	}

	@Test
	public void Should_Return_Declared_Name_When_Annotation_Is_Present_But_No_Prefix_Is_Applied() {
		ExecutionFieldAnnotation annotation = new ExecutionFieldAnnotation(
				new ExecutionFieldAnnotationMockWithoutPrefixAnnotation());
		Assertions.assertThat(annotation.getName()).isEqualTo("name");
	}

}
