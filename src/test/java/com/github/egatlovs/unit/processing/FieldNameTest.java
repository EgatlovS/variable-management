package com.github.egatlovs.unit.processing;

import com.github.egatlovs.mock.FieldNameMockWithNameAndPrefix;
import com.github.egatlovs.mock.FieldNameMockWithoutAnnotation;
import com.github.egatlovs.mock.FieldNameMockWithoutPrefixAnnotation;
import com.github.egatlovs.variablemanager.processing.FieldNameExtractor;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class FieldNameTest {

    @Test
    public void Should_Return_FieldName_When_No_Annotation_Is_Present() {
        FieldNameExtractor fieldName = new FieldNameExtractor();
        Assertions.assertThat(fieldName.getFrom(new FieldNameMockWithoutAnnotation()))
                .isEqualTo("FieldNameMockWithoutAnnotation");
    }

    @Test
    public void Should_Return_Declared_Prefix_And_Name_When_Annotation_Is_Present() {
        FieldNameExtractor fieldName = new FieldNameExtractor();
        Assertions.assertThat(fieldName.getFrom(new FieldNameMockWithNameAndPrefix()))
                .isEqualTo("prefix_name");
    }

    @Test
    public void Should_Return_Declared_Name_When_Annotation_Is_Present_But_No_Prefix_Is_Applied() {
        FieldNameExtractor fieldName = new FieldNameExtractor();
        Assertions.assertThat(fieldName.getFrom(new FieldNameMockWithoutPrefixAnnotation()))
                .isEqualTo("name");
    }

}
