package com.github.egatlovs.unit.processing;

import com.github.egatlovs.mock.ExecutionAnnotationMock;
import com.github.egatlovs.mock.FieldNamesMock;
import com.github.egatlovs.variablemanager.processing.FieldNames;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Set;

public class FieldNamesTest {

    @Test
    public void Should_Return_DeclaredFieldNames() {
        FieldNames fieldNames = new FieldNames();
        Set<String> names = fieldNames.getNames(FieldNamesMock.class);
        Assertions.assertThat(names).contains("notAnnotated", "name", "prefix_name");
    }

    @Test
    public void Should_Return_DeclaredObjectName() {
        FieldNames fieldNames = new FieldNames();
        Set<String> names = fieldNames.getNames(ExecutionAnnotationMock.class);
        Assertions.assertThat(names).contains("ExecutionAnnotationMock");
    }

}
