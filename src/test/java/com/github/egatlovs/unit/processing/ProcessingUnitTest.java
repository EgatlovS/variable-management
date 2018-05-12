package com.github.egatlovs.unit.processing;

import com.github.egatlovs.mock.*;
import com.github.egatlovs.variablemanager.processing.ProcessingUnit;
import org.assertj.core.api.Assertions;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.commons.utils.IoUtil;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;

public class ProcessingUnitTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void Should_Return_Object_Value() {
        ProcessingUnitMockJsonSerialized mock = new ProcessingUnitMockJsonSerialized("foo", "bar", BigDecimal.ONE, "");
        ProcessingUnit processingUnit = new ProcessingUnit();
        VariableMap map = processingUnit.getVariables(mock);
        Assertions.assertThat(map).containsKey("ProcessingUnitMockJsonSerialized");
        Assertions.assertThat(map).containsValue(mock);
    }

    @Test
    public void Should_Return_Field_Values() {
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
    public void Should_Return_Nested_Object_Value() {
        ResultObjectMockNestedObject mock = new ResultObjectMockNestedObject();
        ProcessingUnit processingUnit = new ProcessingUnit();
        VariableMap map = processingUnit.getVariables(mock);
        Assertions.assertThat(map).containsKey("NestedObject");
        Assertions.assertThat(((NestedObject) map.get("NestedObject")).getNestedString()).isEqualTo("nestedString");
    }

    @Test
    public void Should_Return_Nested_Field_Values() {
        ResultObjectMockNested mock = new ResultObjectMockNested();
        ProcessingUnit processingUnit = new ProcessingUnit();
        VariableMap map = processingUnit.getVariables(mock);
        Assertions.assertThat(map).containsKey("nestedString");
        Assertions.assertThat(map).containsValue("nestedString");
    }

    @Test
    public void Should_Return_Object_Name_And_Value_If_Object_Isnt_Annotated() {
        Object mock = new Object();
        ProcessingUnit processingUnit = new ProcessingUnit();
        VariableMap map = processingUnit.getVariables(mock);
        Assertions.assertThat(map).containsKey("Object");
        Assertions.assertThat(map).containsValue(mock);
    }

    @Test
    public void Should_Use_Custom_Serialization_Format() {
        CustomSerializationMock mock = new CustomSerializationMock();
        ProcessingUnit processingUnit = new ProcessingUnit();
        VariableMap map = processingUnit.getVariables(mock);
        Assertions.assertThat(map).containsKey("CustomSerializationMock");
        Assertions.assertThat(map).containsValue(mock);
    }

    @Test
    public void Should_Recognize_File_Values_Not_Initialized() {
        FileValueMock mock = new FileValueMock();
        ProcessingUnit processingUnit = new ProcessingUnit();
        VariableMap map = processingUnit.getVariables(mock);
        Assertions.assertThat(map).containsKey("inputStream");
        Assertions.assertThat(map).containsValue(null);
        Assertions.assertThat(map).containsKey("bytes");
        Assertions.assertThat(map).containsValue(null);
        Assertions.assertThat(map).containsKey("file");
        Assertions.assertThat(map).containsValue(null);
    }

    @Test
    public void Should_Recognize_File_Values() throws IOException {
        FileValueMock mock = new FileValueMock(new byte[10], IoUtil.stringAsInputStream("myString"), folder.newFile("myTestFile"));
        ProcessingUnit processingUnit = new ProcessingUnit();
        VariableMap map = processingUnit.getVariables(mock);
        Assertions.assertThat(map).containsKey("inputStream");
        Assertions.assertThat(map).containsKey("bytes");
        Assertions.assertThat(map).containsKey("file");
        Assertions.assertThat(map.values()).hasSize(3);
    }

}
