package com.github.egatlovs.unit.managers;

import com.github.egatlovs.mock.ManagerFieldMock;
import com.github.egatlovs.util.builder.RuntimeServiceMockBuilder;
import com.github.egatlovs.variablemanager.managers.RuntimeManager;
import org.assertj.core.api.Assertions;
import org.camunda.bpm.engine.RuntimeService;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class RuntimeManagerTest {

    @Test
    public void Should_Remove_Declared_Field_From_Execution() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("someString", "string");
        variables.put("fieldPrefix_fieldName", "annotatedString");
        variables.put("myDecimal", BigDecimal.ONE);
        RuntimeService service = RuntimeServiceMockBuilder.build(variables);
        RuntimeManager manager = new RuntimeManager(service);
        manager.removeVariables(ManagerFieldMock.class, "executionId");

        Assertions.assertThat(service.getVariables("executionId")).isEmpty();
    }

    @Test
    public void Should_Remove_Declared_Field_From_Local_Execution() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("someString", "string");
        variables.put("fieldPrefix_fieldName", "annotatedString");
        variables.put("myDecimal", BigDecimal.ONE);
        RuntimeService service = RuntimeServiceMockBuilder.build(variables);
        RuntimeManager manager = new RuntimeManager(service);
        manager.removeVariablesLocal(ManagerFieldMock.class, "executionId");

        Assertions.assertThat(service.getVariablesLocal("executionId")).isEmpty();
    }

    @Test
    public void Should_Retrieve_Declared_Field_From_Execution() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("someString", "myString");
        variables.put("fieldPrefix_fieldName", "myAnnotated");
        variables.put("myDecimal", BigDecimal.ONE);
        variables.put("ignoredField", new Object());
        RuntimeService service = RuntimeServiceMockBuilder.build(variables);
        RuntimeManager manager = new RuntimeManager(service);
        ManagerFieldMock mock = manager.getVariable(ManagerFieldMock.class, "RuntimeId");

        Assertions.assertThat(mock.getAnnotated()).isEqualTo("myAnnotated");
        Assertions.assertThat(mock.getIgnoredField()).isNull();
        Assertions.assertThat(mock.getSomeString()).isEqualTo("myString");
        Assertions.assertThat(mock.getDecimal()).isEqualTo(BigDecimal.ONE);
    }

    @Test
    public void Should_Retrieve_Declared_Field_From_Local_Execution() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("someString", "myString");
        variables.put("fieldPrefix_fieldName", "myAnnotated");
        variables.put("myDecimal", BigDecimal.ONE);
        variables.put("ignoredField", new Object());
        RuntimeService service = RuntimeServiceMockBuilder.build(variables);
        RuntimeManager manager = new RuntimeManager(service);
        ManagerFieldMock mock = manager.getVariableLocal(ManagerFieldMock.class, "RuntimeId");

        Assertions.assertThat(mock.getAnnotated()).isEqualTo("myAnnotated");
        Assertions.assertThat(mock.getIgnoredField()).isNull();
        Assertions.assertThat(mock.getSomeString()).isEqualTo("myString");
        Assertions.assertThat(mock.getDecimal()).isEqualTo(BigDecimal.ONE);
    }

    @Test
    public void Should_Set_Declared_Field_To_Execution() {
        ManagerFieldMock mock = new ManagerFieldMock();
        RuntimeService service = RuntimeServiceMockBuilder.build();
        RuntimeManager manager = new RuntimeManager(service);
        manager.setVariable(mock, "executionId");

        Assertions.assertThat(service.getVariable("executionId", "someString")).isEqualTo("string");
        Assertions.assertThat(service.getVariable("executionId", "ignoredField")).isNull();
        Assertions.assertThat(service.getVariable("executionId", "fieldPrefix_fieldName")).isEqualTo("annotatedString");
        Assertions.assertThat(service.getVariable("executionId", "myDecimal")).isEqualTo(BigDecimal.ONE);
    }

    @Test
    public void Should_Set_Declared_Field_To_Local_Execution() {
        ManagerFieldMock mock = new ManagerFieldMock();
        RuntimeService service = RuntimeServiceMockBuilder.build();
        RuntimeManager manager = new RuntimeManager(service);
        manager.setVariableLocal(mock, "executionId");

        Assertions.assertThat(service.getVariableLocal("executionId", "someString")).isEqualTo("string");
        Assertions.assertThat(service.getVariableLocal("executionId", "ignoredField")).isNull();
        Assertions.assertThat(service.getVariableLocal("executionId", "fieldPrefix_fieldName"))
                .isEqualTo("annotatedString");
        Assertions.assertThat(service.getVariableLocal("executionId", "myDecimal")).isEqualTo(BigDecimal.ONE);
    }

}
