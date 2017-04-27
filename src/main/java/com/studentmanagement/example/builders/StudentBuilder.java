package com.studentmanagement.example.builders;

import com.studentmanagement.example.models.Grade;
import com.studentmanagement.example.models.Student;

import java.util.List;

/**
 * Created by apiriu on 4/15/2017.
 */
public class StudentBuilder {
    private Student instance = new Student();

    public StudentBuilder() {}

    public StudentBuilder firstName(String inputFirstName) {
        this.instance.setFirstName(inputFirstName);
        return this;
    }

    public StudentBuilder lastName(String inputLastName) {
        this.instance.setLastName(inputLastName);
        return this;
    }

    public StudentBuilder grades(List<Grade> inputGrades) {
        this.instance.setGrades(inputGrades);
        return this;
    }

    public StudentBuilder id(Long inputId) {
        this.instance.setId(inputId);
        return this;
    }

    public Student build() {
        assert this.instance.getFirstName() != null;
        assert this.instance.getLastName() != null;
        assert this.instance.getFirstName().length() != 0;
        assert this.instance.getLastName().length() != 0;
        return this.instance;
    }
}
