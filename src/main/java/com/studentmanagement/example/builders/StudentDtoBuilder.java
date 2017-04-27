package com.studentmanagement.example.builders;

import com.studentmanagement.example.DTO.StudentDto;

/**
 * Created by apiriu on 4/15/2017.
 */
public class StudentDtoBuilder {
    private StudentDto instance = new StudentDto();

    public StudentDtoBuilder() {}

    public StudentDtoBuilder id(Long inputId) {
        this.instance.id = inputId;
        return this;
    }

    public StudentDtoBuilder firstName(String inputFirstName) {
        this.instance.firstName = inputFirstName;
        return this;
    }

    public StudentDtoBuilder lastName(String inputLastName) {
        this.instance.lastName = inputLastName;
        return this;
    }

    public StudentDto build() {
        assert this.instance.id != null;
        assert this.instance.firstName != null;
        assert this.instance.lastName != null;
        assert this.instance.firstName.length() != 0;
        assert this.instance.lastName.length() != 0;
        return this.instance;
    }
}
