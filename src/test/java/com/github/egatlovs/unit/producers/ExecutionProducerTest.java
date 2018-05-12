package com.github.egatlovs.unit.producers;

import com.github.egatlovs.variablemanager.producers.ExecutionProducer;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class ExecutionProducerTest {

    @Test
    public void Should_Return_Null_If_No_Execution_Is_Present() {
        ExecutionProducer producer = new ExecutionProducer();
        Assertions.assertThat(producer.getDelegateExecution()).isNull();
    }

}
