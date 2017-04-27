package com.studentmanagement.example.builders;

import com.studentmanagement.example.DTO.GradeDto;

/**
 * Created by apiriu on 4/15/2017.
 */
public class GradeDtoBuilder {
    private GradeDto instance = new GradeDto();

    public GradeDtoBuilder() {}

    public GradeDtoBuilder id(Long inputId) {
        this.instance.id = inputId;
        return this;
    }

    public GradeDtoBuilder rate(Integer inputRate) {
        this.instance.rate = inputRate;
        return this;
    }

    public GradeDtoBuilder description(String inputDescription) {
        this.instance.description = inputDescription;
        return this;
    }

    public GradeDto build() {
        assert this.instance.id != null;
        assert this.instance.rate != null;
        assert this.instance.description != null;
        assert this.instance.description.length() != 0;
        return instance;
    }
}
