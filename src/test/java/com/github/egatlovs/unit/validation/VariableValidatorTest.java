package com.github.egatlovs.unit.validation;

import java.util.Arrays;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.github.egatlovs.mock.ValidationMock;
import com.github.egatlovs.variablemanager.exceptions.ViolationException;
import com.github.egatlovs.variablemanager.validation.VariableValidator;

public class VariableValidatorTest {

	@Test
	public void Should_Validate_MockObject() {
		ValidationMock mock = new ValidationMock();
		mock.setNotNullString(new String());
		String[] array = { "1", "2", "3", "4", "5" };
		mock.setSizeList(Arrays.asList(array));
		VariableValidator.validate(mock);
	}

	@Test
	public void Should_Throw_Exception_Validating_MockObject() {
		ValidationMock mock = new ValidationMock();
		try {
			VariableValidator.validate(mock);
		} catch (Exception e) {
			Assertions.assertThat(e).isInstanceOf(ViolationException.class);
		}
	}

}
