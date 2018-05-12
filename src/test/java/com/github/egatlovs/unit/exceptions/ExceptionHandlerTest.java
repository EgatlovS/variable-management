package com.github.egatlovs.unit.exceptions;

import com.github.egatlovs.variablemanager.exceptions.ExceptionHandler;
import com.github.egatlovs.variablemanager.exceptions.ResultObjectException;
import com.github.egatlovs.variablemanager.exceptions.VariableProcessingException;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class ExceptionHandlerTest {

    @Test
    public void Should_Create_ResultObjectException_By_Class() {

        try {
            ExceptionHandler.createResultObjectException(new InstantiationException(), String.class);
        } catch (Exception e) {
            Assertions.assertThat(e).isInstanceOf(ResultObjectException.class);
            Assertions.assertThat(e).hasMessageStartingWith("Error received while");
        }
        try {
            ExceptionHandler.createResultObjectException(new IllegalArgumentException(), String.class);
        } catch (Exception e) {
            Assertions.assertThat(e).isInstanceOf(ResultObjectException.class);
            Assertions.assertThat(e).hasMessageStartingWith("Tried to instantiate");
        }
        try {
            ExceptionHandler.createResultObjectException(new InvocationTargetException(new Exception()), String.class);
        } catch (Exception e) {
            Assertions.assertThat(e).isInstanceOf(ResultObjectException.class);
            Assertions.assertThat(e).hasMessageStartingWith("Instantiation of object");
        }
        try {
            ExceptionHandler.createResultObjectException(new IllegalAccessException(), String.class);
        } catch (Exception e) {
            Assertions.assertThat(e).isInstanceOf(ResultObjectException.class);
            Assertions.assertThat(e).hasMessageStartingWith("Tried to instantiate");
        }
        try {
            ExceptionHandler.createResultObjectException(new NoSuchMethodException(), String.class);
        } catch (Exception e) {
            Assertions.assertThat(e).isInstanceOf(ResultObjectException.class);
            Assertions.assertThat(e).hasMessageStartingWith("Tried to instantiate");
        }
        try {
            ExceptionHandler.createResultObjectException(new SecurityException(), String.class);
        } catch (Exception e) {
            Assertions.assertThat(e).isInstanceOf(ResultObjectException.class);
            Assertions.assertThat(e).hasMessageStartingWith("Tried to instantiate");
        }
        try {
            ExceptionHandler.createResultObjectException(new Exception(), String.class);
        } catch (Exception e) {
            Assertions.assertThat(e).isInstanceOf(ResultObjectException.class);
            Assertions.assertThat(e).hasMessageStartingWith("Unknown");
        }
    }

    @Test
    public void Should_Create_ResultObjectException_By_Field_And_Object() {
        try {
            ExceptionHandler.createResultObjectException(new IllegalAccessException(),
                    String.class.getDeclaredFields()[0], "");
        } catch (Exception e) {
            Assertions.assertThat(e).isInstanceOf(ResultObjectException.class);
            Assertions.assertThat(e).hasMessageStartingWith("Tried to access field value but was not accessible");
        }
        try {
            ExceptionHandler.createResultObjectException(new IllegalArgumentException(),
                    String.class.getDeclaredFields()[0], "");
        } catch (Exception e) {
            Assertions.assertThat(e).isInstanceOf(ResultObjectException.class);
            Assertions.assertThat(e).hasMessageStartingWith("Tried to access field value on wrong object");
        }
        try {
            ExceptionHandler.createResultObjectException(new Exception(), String.class.getDeclaredFields()[0],
                    "");
        } catch (Exception e) {
            Assertions.assertThat(e).isInstanceOf(ResultObjectException.class);
            Assertions.assertThat(e).hasMessageStartingWith("Unknown");
        }
    }

    @Test
    public void Should_Create_VariableProcessingException() {
        try {
            ExceptionHandler.createVariableProcessingException(new IllegalAccessException(),
                    String.class.getDeclaredFields()[0], "");
        } catch (Exception e) {
            Assertions.assertThat(e).isInstanceOf(VariableProcessingException.class);
            Assertions.assertThat(e).hasMessageStartingWith("Tried to access field value but was not accessible");
        }
        try {
            ExceptionHandler.createVariableProcessingException(new IllegalArgumentException(),
                    String.class.getDeclaredFields()[0], "");
        } catch (Exception e) {
            Assertions.assertThat(e).isInstanceOf(VariableProcessingException.class);
            Assertions.assertThat(e).hasMessageStartingWith("Tried to access field value on wrong object");
        }
        try {
            ExceptionHandler.createVariableProcessingException(new Exception(), String.class.getDeclaredFields()[0],
                    "");
        } catch (Exception e) {
            Assertions.assertThat(e).isInstanceOf(VariableProcessingException.class);
            Assertions.assertThat(e).hasMessageStartingWith("Unknown");
        }
    }

}
