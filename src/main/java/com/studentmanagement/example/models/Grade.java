package com.studentmanagement.example.models;

import com.studentmanagement.example.builders.GradeBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "grade")
public class Grade implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private Integer rate;

    @Column(nullable = true)
    private String description;

    @ManyToOne
    @JoinColumn(name = "student")
    private Student student;

    public Grade() {}

    public Grade(Integer inputRate, String inputDescription) {
        this.rate = inputRate;
        this.description = inputDescription;
        this.student = null;
    }

    public GradeBuilder builder() {
        return new GradeBuilder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
