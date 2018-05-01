package com.github.egatlovs.mock;

import java.math.BigDecimal;

import com.github.egatlovs.variablemanager.annotations.FieldName;
import com.github.egatlovs.variablemanager.annotations.Ignore;

public class ManagerFieldMock {

	private String someString = "string";
	@FieldName(name = "fieldName", prefix = "fieldPrefix")
	private String annotated = "annotatedString";
	@FieldName(name = "myDecimal")
	private BigDecimal decimal = BigDecimal.ONE;
	@Ignore
	private Object ignoredField;

	public String getSomeString() {
		return someString;
	}

	public void setSomeString(String someString) {
		this.someString = someString;
	}

	public String getAnnotated() {
		return annotated;
	}

	public void setAnnotated(String annotated) {
		this.annotated = annotated;
	}

	public BigDecimal getDecimal() {
		return decimal;
	}

	public void setDecimal(BigDecimal decimal) {
		this.decimal = decimal;
	}

	public Object getIgnoredField() {
		return ignoredField;
	}

	public void setIgnoredField(Object ignoredField) {
		this.ignoredField = ignoredField;
	}

}
