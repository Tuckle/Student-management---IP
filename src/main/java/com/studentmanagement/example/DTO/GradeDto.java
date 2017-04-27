package com.studentmanagement.example.DTO;

import com.studentmanagement.example.builders.GradeDtoBuilder;

public class GradeDto extends CreatingGradeDto{
    public Long id;

    public GradeDto() {}

    public GradeDtoBuilder builder() {
        return new GradeDtoBuilder();
    }
}
