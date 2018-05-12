package com.github.egatlovs.unit.managers;

import com.github.egatlovs.mock.ManagerFieldMock;
import com.github.egatlovs.util.builder.TaskServiceMockBuilder;
import com.github.egatlovs.variablemanager.managers.TaskManager;
import org.assertj.core.api.Assertions;
import org.camunda.bpm.engine.TaskService;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class TaskManagerTest {

    @Test
    public void Should_Remove_Declared_Field_From_Execution() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("someString", "string");
        variables.put("fieldPrefix_fieldName", "annotatedString");
        variables.put("myDecimal", BigDecimal.ONE);
        TaskService service = TaskServiceMockBuilder.build(variables);
        TaskManager manager = new TaskManager(service);
        manager.removeVariables(ManagerFieldMock.class, "taskId");

        Assertions.assertThat(service.getVariables("taskId")).isEmpty();
    }

    @Test
    public void Should_Remove_Declared_Field_From_Local_Execution() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("someString", "string");
        variables.put("fieldPrefix_fieldName", "annotatedString");
        variables.put("myDecimal", BigDecimal.ONE);
        TaskService service = TaskServiceMockBuilder.build(variables);
        TaskManager manager = new TaskManager(service);
        manager.removeVariablesLocal(ManagerFieldMock.class, "taskId");

        Assertions.assertThat(service.getVariablesLocal("taskId")).isEmpty();
    }

    @Test
    public void Should_Retrieve_Declared_Field_From_Execution() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("someString", "myString");
        variables.put("fieldPrefix_fieldName", "myAnnotated");
        variables.put("myDecimal", BigDecimal.ONE);
        variables.put("ignoredField", new Object());
        TaskService service = TaskServiceMockBuilder.build(variables);
        TaskManager manager = new TaskManager(service);
        ManagerFieldMock mock = manager.getVariable(ManagerFieldMock.class, "taskId");

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
        TaskService service = TaskServiceMockBuilder.build(variables);
        TaskManager manager = new TaskManager(service);
        ManagerFieldMock mock = manager.getVariableLocal(ManagerFieldMock.class, "taskId");

        Assertions.assertThat(mock.getAnnotated()).isEqualTo("myAnnotated");
        Assertions.assertThat(mock.getIgnoredField()).isNull();
        Assertions.assertThat(mock.getSomeString()).isEqualTo("myString");
        Assertions.assertThat(mock.getDecimal()).isEqualTo(BigDecimal.ONE);
    }

    @Test
    public void Should_Set_Declared_Field_To_Execution() {
        ManagerFieldMock mock = new ManagerFieldMock();
        TaskService service = TaskServiceMockBuilder.build();
        TaskManager manager = new TaskManager(service);
        manager.setVariable(mock, "taskId");

        Assertions.assertThat(service.getVariable("taskId", "someString")).isEqualTo("string");
        Assertions.assertThat(service.getVariable("taskId", "ignoredField")).isNull();
        Assertions.assertThat(service.getVariable("taskId", "fieldPrefix_fieldName")).isEqualTo("annotatedString");
        Assertions.assertThat(service.getVariable("taskId", "myDecimal")).isEqualTo(BigDecimal.ONE);
    }

    @Test
    public void Should_Set_Declared_Field_To_Local_Execution() {
        ManagerFieldMock mock = new ManagerFieldMock();
        TaskService service = TaskServiceMockBuilder.build();
        TaskManager manager = new TaskManager(service);
        manager.setVariableLocal(mock, "taskId");

        Assertions.assertThat(service.getVariableLocal("taskId", "someString")).isEqualTo("string");
        Assertions.assertThat(service.getVariableLocal("taskId", "ignoredField")).isNull();
        Assertions.assertThat(service.getVariableLocal("taskId", "fieldPrefix_fieldName")).isEqualTo("annotatedString");
        Assertions.assertThat(service.getVariableLocal("taskId", "myDecimal")).isEqualTo(BigDecimal.ONE);
    }

    @Test
    public void Should_Have_Default_Constructor() {
        TaskManager manager = new TaskManager();
        Assertions.assertThat(manager).isNotNull();
    }

    @Test
    public void Should_Allow_Access_To_Wrapped_Service() {
        TaskManager manager = new TaskManager();
        Assertions.assertThat(manager.getExecutionService()).isNull();
    }

    @Test
    public void Should_Allow_To_Manipulate_Wrapped_Service() {
        TaskManager manager = new TaskManager();
        Assertions.assertThat(manager.getExecutionService()).isNull();
        TaskService service = TaskServiceMockBuilder.build();
        manager.setExecutionService(service);
        Assertions.assertThat(manager.getExecutionService()).isEqualTo(service);
    }

}
