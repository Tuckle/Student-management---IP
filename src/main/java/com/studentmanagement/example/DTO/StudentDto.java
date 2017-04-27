package com.studentmanagement.example.DTO;

import com.studentmanagement.example.builders.StudentDtoBuilder;

public class StudentDto extends CreatingStudentDto {
    public Long id;

    public StudentDto() {}

    public StudentDtoBuilder builder() {
        return new StudentDtoBuilder();
    }
}
