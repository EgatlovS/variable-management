package com.github.egatlovs.mock;

import com.github.egatlovs.variablemanager.annotations.FieldName;
import com.github.egatlovs.variablemanager.annotations.Ignore;
import com.github.egatlovs.variablemanager.annotations.ObjectValue;

@ObjectValue(storeFields = true)
public class FieldNamesMock {

    private String notAnnotated;
    @FieldName(name = "name", prefix = "prefix")
    private String annotated;
    @FieldName(name = "name")
    private String noPrefix;
    @Ignore
    private String ignored;

}
