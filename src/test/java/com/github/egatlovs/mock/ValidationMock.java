package com.github.egatlovs.mock;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ValidationMock {

	@NotNull
	private String notNullString;

	@NotNull
	@Size(min = 5)
	private List<String> sizeList;

	public String getNotNullString() {
		return notNullString;
	}

	public void setNotNullString(String notNullString) {
		this.notNullString = notNullString;
	}

	public List<String> getSizeList() {
		return sizeList;
	}

	public void setSizeList(List<String> sizeList) {
		this.sizeList = sizeList;
	}

}
