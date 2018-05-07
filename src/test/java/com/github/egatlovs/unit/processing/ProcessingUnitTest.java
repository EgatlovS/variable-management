package com.github.egatlovs.unit.processing;

import com.github.egatlovs.mock.*;
import com.github.egatlovs.variablemanager.processing.ProcessingUnit;
import org.assertj.core.api.Assertions;
import org.camunda.bpm.engine.variable.VariableMap;
import org.junit.Test;

import java.math.BigDecimal;

public class ProcessingUnitTest {

    @Test
    public void Should_Add_Return_Object_Value() {
        ProcessingUnitMockJsonSerialized mock = new ProcessingUnitMockJsonSerialized("foo", "bar", BigDecimal.ONE, "");
        ProcessingUnit processingUnit = new ProcessingUnit();
        VariableMap map = processingUnit.getVariables(mock);
        Assertions.assertThat(map).containsKey("ProcessingUnitMockJsonSerialized");
        Assertions.assertThat(map).containsValue(mock);
    }

    @Test
    public void Should_Add_Return_Field_Values() {
        ProcessingUnitMock mock = new ProcessingUnitMock("foo", "bar", BigDecimal.ONE, "");
        ProcessingUnit processingUnit = new ProcessingUnit();
        VariableMap map = processingUnit.getVariables(mock);
        Assertions.assertThat(map).containsKeys("someString", "fieldPrefix_fieldName", "myDecimal");
        Assertions.assertThat(map).containsValue("foo");
        Assertions.assertThat(map).containsValue("bar");
        Assertions.assertThat(map).containsValue(BigDecimal.ONE);
        Assertions.assertThat(map).hasSize(3);
    }

    @Test
    public void Should_Add_Return_Nested_Object_Value() {
        ResultObjectMockNestedObject mock = new ResultObjectMockNestedObject();
        ProcessingUnit processingUnit = new ProcessingUnit();
        VariableMap map = processingUnit.getVariables(mock);
        Assertions.assertThat(map).containsKey("NestedObject");
        Assertions.assertThat(((NestedObject) map.get("NestedObject")).getNestedString()).isEqualTo("nestedString");
    }

    @Test
    public void Should_Add_Return_Nested_Field_Values() {
        ResultObjectMockNested mock = new ResultObjectMockNested();
        ProcessingUnit processingUnit = new ProcessingUnit();
        VariableMap map = processingUnit.getVariables(mock);
        Assertions.assertThat(map).containsKey("nestedString");
        Assertions.assertThat(map).containsValue("nestedString");
    }
}
