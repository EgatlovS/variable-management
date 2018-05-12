package com.github.egatlovs.unit.managers;

import com.github.egatlovs.variablemanager.managers.ExecutionManager;
import com.github.egatlovs.variablemanager.managers.RuntimeManager;
import com.github.egatlovs.variablemanager.managers.TaskManager;
import com.github.egatlovs.variablemanager.managers.VariableManager;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class VariableManagerTest {

    @Test
    public void Should_Allow_Access_To_ExecutionManager(){
        VariableManager manager = new VariableManager();
        Assertions.assertThat(manager.getExecutionManager()).isNull();
    }

    @Test
    public void Should_Allow_Access_To_RuntimeManager(){
        VariableManager manager = new VariableManager();
        Assertions.assertThat(manager.getRuntimeManager()).isNull();
    }

    @Test
    public void Should_Allow_Access_To_TaskManager(){
        VariableManager manager = new VariableManager();
        Assertions.assertThat(manager.getTaskManager()).isNull();
    }

    @Test
    public void Should_Have_Default_Constuctor(){
        VariableManager manager = new VariableManager();
        Assertions.assertThat(manager).isNotNull();
    }

    @Test
    public void Should_Allow_To_Be_Initialized_With_Managers(){
        RuntimeManager runtimeManager = new RuntimeManager();
        TaskManager taskManager = new TaskManager();
        ExecutionManager executionManager = new ExecutionManager();
        VariableManager manager = new VariableManager(executionManager, runtimeManager, taskManager);
        Assertions.assertThat(manager).isNotNull();
        Assertions.assertThat(manager.getRuntimeManager()).isEqualTo(runtimeManager);
        Assertions.assertThat(manager.getExecutionManager()).isEqualTo(executionManager);
        Assertions.assertThat(manager.getTaskManager()).isEqualTo(taskManager);
    }

    @Test
    public void Should_Allow_To_Manipulate_RuntimeManager(){
        VariableManager manager = new VariableManager();
        Assertions.assertThat(manager.getRuntimeManager()).isNull();
        RuntimeManager runtimeManager = new RuntimeManager();
        manager.setRuntimeManager(runtimeManager);
        Assertions.assertThat(manager.getRuntimeManager()).isEqualTo(runtimeManager);
    }

    @Test
    public void Should_Allow_To_Manipulate_ExecutionManager(){
        VariableManager manager = new VariableManager();
        Assertions.assertThat(manager.getRuntimeManager()).isNull();
        ExecutionManager executionManager = new ExecutionManager();
        manager.setExecutionManager(executionManager);
        Assertions.assertThat(manager.getExecutionManager()).isEqualTo(executionManager);
    }

    @Test
    public void Should_Allow_To_Manipulate_TaskManager(){
        VariableManager manager = new VariableManager();
        Assertions.assertThat(manager.getTaskManager()).isNull();
        TaskManager taskManager = new TaskManager();
        manager.setTaskManager(taskManager);
        Assertions.assertThat(manager.getTaskManager()).isEqualTo(taskManager);
    }

}
