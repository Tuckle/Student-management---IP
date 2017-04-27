package com.studentmanagement.example.builders;

import com.studentmanagement.example.models.Grade;
import com.studentmanagement.example.models.Student;

/**
 * Created by apiriu on 4/15/2017.
 */
public class GradeBuilder {
    private Grade instance = new Grade();

    public GradeBuilder() {}

    public GradeBuilder rate(Integer inputRate) {
        instance.setRate(inputRate);
        return this;
    }

    public GradeBuilder description(String inputDescription) {
        instance.setDescription(inputDescription);
        return this;
    }

    public GradeBuilder student(Student inputStudent) {
        instance.setStudent(inputStudent);
        return this;
    }

    public GradeBuilder id(Long inputId) {
        instance.setId(inputId);
        return this;
    }

    public Grade build() {
        assert instance.getRate() != null;
        assert instance.getDescription() != null;
        assert instance.getDescription().length() != 0;
        return this.instance;
    }
}
