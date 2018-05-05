package com.github.egatlovs.mock;

import com.github.egatlovs.variablemanager.annotations.FieldName;
import com.github.egatlovs.variablemanager.annotations.Ignore;

import java.math.BigDecimal;

public class VariablesProcessorMock {

    private String someString = "string";
    @FieldName(name = "fieldName", prefix = "fieldPrefix")
    private String annotated = "annotatedString";
    @FieldName(name = "myDecimal")
    private BigDecimal decimal = BigDecimal.ONE;
    @Ignore
    private Object ignoredField;

}
