package com.github.egatlovs.mock;

import com.github.egatlovs.variablemanager.annotations.ExecutionField;
import com.github.egatlovs.variablemanager.annotations.Ignore;

public class FieldNamesMock {

	private String notAnnotated;
	@ExecutionField(name = "name", prefix = "prefix")
	private String annotated;
	@ExecutionField(name = "name")
	private String noPrefix;
	@Ignore
	private String ignored;

}
