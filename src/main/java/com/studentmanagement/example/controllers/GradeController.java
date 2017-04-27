package com.studentmanagement.example.controllers;

import com.studentmanagement.example.DTO.CreatingGradeDto;
import com.studentmanagement.example.DTO.GradeDto;
import com.studentmanagement.example.DTO.StudentDto;
import com.studentmanagement.example.models.Grade;
import com.studentmanagement.example.models.Student;
import com.studentmanagement.example.services.GradeService;
import com.studentmanagement.example.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.table.TableRowSorter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/students")
public class GradeController {
    @Autowired
    private GradeService gradeService;

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/{studentId}/grades", method = RequestMethod.GET)
    public ResponseEntity<List<GradeDto>> getGradesByStudentId(@PathVariable("studentId") Long studentId) {
        Student student = this.studentService.getById(studentId);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        student.getGrades().size();
        List<Grade> grades = student.getGrades();
        grades.size();

        List<GradeDto> gradeDtos = new ArrayList<>();
        for (Grade grade : grades) {
            GradeDto gradeDto = toDto(grade);
            gradeDtos.add(gradeDto);
        }
        return new ResponseEntity<>(gradeDtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/{studentId}/grades", method = RequestMethod.POST)
    public ResponseEntity<GradeDto> addGrade(@PathVariable("studentId") Long studentId, @RequestBody CreatingGradeDto dto) {
        Student student = this.studentService.getById(studentId);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Grade newGrade = toCreatingModel(dto);
        newGrade.setStudent(student);

        Grade savedGrade = this.gradeService.save(newGrade);

        return new ResponseEntity<>(toDto(savedGrade), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{studentId}/grades/{gradeId}", method = RequestMethod.PUT)
    public ResponseEntity<GradeDto> updateGrade(@PathVariable("studentId") Long studentId,
                                                @PathVariable("gradeId") Long gradeId,
                                                @RequestBody CreatingGradeDto dto) {
        Student student = studentService.getById(studentId);
        if(student == null) {
            return new ResponseEntity<GradeDto>(HttpStatus.NO_CONTENT);
        }
        boolean found = false;
        for (Grade grade : student.getGrades()) {
            if(grade.getId().equals(gradeId)) {
                found = true;
                break;
            }
        }
        if(!found) {
            return new ResponseEntity<GradeDto>(HttpStatus.NO_CONTENT);
        }
        Grade gradeCopy = toCreatingModel(dto);
        gradeCopy.setId(gradeId);
        gradeCopy.setStudent(student);
        gradeService.save(gradeCopy);
        return new ResponseEntity<GradeDto>(toDto(gradeCopy), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/{studentId}/grades/{gradeId}", method = RequestMethod.DELETE)
    public ResponseEntity<GradeDto> deleteGrade(@PathVariable("studentId") Long studentId,
                                                @PathVariable("gradeId") Long gradeId) {
        Student student = studentService.getById(studentId);
        if(student == null) {
            return new ResponseEntity<GradeDto>(HttpStatus.NO_CONTENT);
        }
        List<Grade> grades = student.getGrades();
        boolean found = false;
        for(Grade grade : grades) {
            if(grade.getId().equals(gradeId)) {
                grades.remove(grade);
                found = true;
                break;
            }
        }
        if(!found) {
            return new ResponseEntity<GradeDto>(HttpStatus.NO_CONTENT);
        }
        student.setGrades(grades);
        studentService.save(student);
        return new ResponseEntity<GradeDto>(HttpStatus.ACCEPTED);
    }

    private GradeDto toDto(Grade grade) {
        return new GradeDto().builder().id(grade.getId()).rate(grade.getRate()).description(grade.getDescription()).build();
    }

    private Grade toCreatingModel(CreatingGradeDto dto) {
        return new Grade().builder().rate(dto.rate).description(dto.description).build();
    }
}

