package com.github.egatlovs.unit.processing;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.github.egatlovs.mock.ExecutionAnnotationMock;
import com.github.egatlovs.variablemanager.annotations.StoreStrategies;
import com.github.egatlovs.variablemanager.processing.ExecutionAnnotation;

public class ExecutionAnnotationTest {

	@Test
	public void Should_Return_Default_Values_When_No_Annotation_Is_Present() {
		ExecutionAnnotation annotation = new ExecutionAnnotation(null);
		Assertions.assertThat(annotation.getStoreStrategy()).isEqualTo(StoreStrategies.OBJECT);
		Assertions.assertThat(annotation.isStoreFields()).isTrue();
	}

	@Test
	public void Should_Return_Declared_Values_When_Annotation_Is_Present() {
		ExecutionAnnotation annotation = new ExecutionAnnotation(new ExecutionAnnotationMock());
		Assertions.assertThat(annotation.getStoreStrategy()).isEqualTo(StoreStrategies.JSON);
		Assertions.assertThat(annotation.isStoreFields()).isFalse();
	}

}
