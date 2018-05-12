package com.github.egatlovs.unit.managers;

import com.github.egatlovs.mock.ManagerFieldMock;
import com.github.egatlovs.util.builder.ExecutionMockBuilder;
import com.github.egatlovs.variablemanager.managers.ExecutionManager;
import org.assertj.core.api.Assertions;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ExecutionManagerTest {

    @Test
    public void Should_Remove_Declared_Field_From_Execution() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("someString", "string");
        variables.put("fieldPrefix_fieldName", "annotatedString");
        variables.put("myDecimal", BigDecimal.ONE);
        DelegateExecution execution = ExecutionMockBuilder.build(variables);
        ExecutionManager manager = new ExecutionManager(execution);
        manager.removeVariables(ManagerFieldMock.class);

        Assertions.assertThat(execution.getVariables()).isEmpty();
    }

    @Test
    public void Should_Remove_Declared_Field_From_Local_Execution() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("someString", "string");
        variables.put("fieldPrefix_fieldName", "annotatedString");
        variables.put("myDecimal", BigDecimal.ONE);
        DelegateExecution execution = ExecutionMockBuilder.build(variables);
        ExecutionManager manager = new ExecutionManager(execution);
        manager.removeVariablesLocal(ManagerFieldMock.class);

        Assertions.assertThat(execution.getVariables()).isEmpty();
    }

    @Test
    public void Should_Retrieve_Declared_Field_From_Execution() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("someString", "myString");
        variables.put("fieldPrefix_fieldName", "myAnnotated");
        variables.put("myDecimal", BigDecimal.ONE);
        variables.put("ignoredField", new Object());
        DelegateExecution execution = ExecutionMockBuilder.build(variables);
        ExecutionManager manager = new ExecutionManager(execution);
        ManagerFieldMock mock = manager.getVariable(ManagerFieldMock.class);

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
        DelegateExecution execution = ExecutionMockBuilder.build(variables);
        ExecutionManager manager = new ExecutionManager(execution);
        ManagerFieldMock mock = manager.getVariableLocal(ManagerFieldMock.class);

        Assertions.assertThat(mock.getAnnotated()).isEqualTo("myAnnotated");
        Assertions.assertThat(mock.getIgnoredField()).isNull();
        Assertions.assertThat(mock.getSomeString()).isEqualTo("myString");
        Assertions.assertThat(mock.getDecimal()).isEqualTo(BigDecimal.ONE);
    }

    @Test
    public void Should_Set_Declared_Field_To_Execution() {
        ManagerFieldMock mock = new ManagerFieldMock();
        DelegateExecution execution = ExecutionMockBuilder.build();
        ExecutionManager manager = new ExecutionManager(execution);
        manager.setVariable(mock);

        Assertions.assertThat(execution.getVariable("someString")).isEqualTo("string");
        Assertions.assertThat(execution.getVariable("ignoredField")).isNull();
        Assertions.assertThat(execution.getVariable("fieldPrefix_fieldName")).isEqualTo("annotatedString");
        Assertions.assertThat(execution.getVariable("myDecimal")).isEqualTo(BigDecimal.ONE);
    }

    @Test
    public void Should_Set_Declared_Field_To_Local_Execution() {
        ManagerFieldMock mock = new ManagerFieldMock();
        DelegateExecution execution = ExecutionMockBuilder.build();
        ExecutionManager manager = new ExecutionManager(execution);
        manager.setVariableLocal(mock);

        Assertions.assertThat(execution.getVariableLocal("someString")).isEqualTo("string");
        Assertions.assertThat(execution.getVariableLocal("ignoredField")).isNull();
        Assertions.assertThat(execution.getVariableLocal("fieldPrefix_fieldName")).isEqualTo("annotatedString");
        Assertions.assertThat(execution.getVariableLocal("myDecimal")).isEqualTo(BigDecimal.ONE);
    }

    @Test
    public void Should_Have_Default_Constructor() {
        ExecutionManager manager = new ExecutionManager();
        Assertions.assertThat(manager).isNotNull();
    }

    @Test
    public void Should_Allow_Access_To_Wrapped_Service() {
        ExecutionManager manager = new ExecutionManager();
        Assertions.assertThat(manager.getExecutionService()).isNull();
    }
    @Test
    public void Should_Allow_To_Manipulate_Wrapped_Service() {
        ExecutionManager manager = new ExecutionManager();
        Assertions.assertThat(manager.getExecutionService()).isNull();
        DelegateExecution execution = ExecutionMockBuilder.build();
        manager.setExecutionService(execution);
        Assertions.assertThat(manager.getExecutionService()).isEqualTo(execution);
    }

}
