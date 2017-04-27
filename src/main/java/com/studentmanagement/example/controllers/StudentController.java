package com.studentmanagement.example.controllers;

import com.studentmanagement.example.DTO.CreatingStudentDto;
import com.studentmanagement.example.DTO.StudentDto;
import com.studentmanagement.example.models.Student;
import com.studentmanagement.example.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/students")
public class StudentController {
    @Autowired
    private StudentService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<StudentDto>> get() {
        List<Student> students = this.service.getAll();
        if (students.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<StudentDto> result = new ArrayList<>();

        for (Student student : students) {
            StudentDto dto = toDto(student);
            result.add(dto);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<StudentDto> addStudent(@RequestBody CreatingStudentDto studentDto) {
        Student student = toCreatingModel(studentDto);
        Student savedStudent = this.service.save(student);
        return new ResponseEntity<>(toDto(savedStudent), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<StudentDto> getStudentById(@PathVariable("id") Long id) {
        Student student = this.service.getById(id);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(toDto(student), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Student> updateStudent(@PathVariable("id") Long id, @RequestBody CreatingStudentDto dto) {
        if(id == null) {
            return new ResponseEntity<Student>(HttpStatus.NOT_ACCEPTABLE);
        }
        if(dto.firstName == null || dto.lastName == null ||
                dto.firstName.length() == 0 || dto.lastName.length() == 0) {
            return new ResponseEntity<Student>(HttpStatus.NOT_ACCEPTABLE);
        }
        Student previousStudent = service.getById(id);
        if(previousStudent == null) {
            return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
        }
        Student studentCopy = toCreatingModel(dto);
        studentCopy.setId(id);
        studentCopy.setGrades(previousStudent.getGrades());
        this.service.save(studentCopy);
        return new ResponseEntity<Student>(studentCopy, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public HttpStatus deleteStudent(@PathVariable("id") Long id) {
        if(this.service.getById(id) == null) {
            return HttpStatus.NO_CONTENT;
        }
        this.service.delete(id);
        return HttpStatus.OK;
    }

    private StudentDto toDto(Student student) {
        return new StudentDto().builder().firstName(student.getFirstName()).lastName(student.getLastName()).id(student.getId()).build();
    }

    private Student toCreatingModel(CreatingStudentDto dto) {
        return new Student().builder().firstName(dto.firstName).lastName(dto.lastName).build();
    }
}
