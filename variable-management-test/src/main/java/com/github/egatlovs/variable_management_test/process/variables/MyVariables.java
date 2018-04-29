package com.github.egatlovs.variable_management_test.process.variables;

import java.util.List;
import java.util.Map;

public class MyVariables {

	private String stringField;
	private int integer;
	private List<String> list;
	private Map<String, Object> map;

	public MyVariables(String stringField, int integer, List<String> list, Map<String, Object> map) {
		this.stringField = stringField;
		this.integer = integer;
		this.list = list;
		this.map = map;
	}

	public MyVariables() {
	}

	public String getStringField() {
		return stringField;
	}

	public void setStringField(String stringField) {
		this.stringField = stringField;
	}

	public int getInteger() {
		return integer;
	}

	public void setInteger(int integer) {
		this.integer = integer;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

}
