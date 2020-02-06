package com.school.controller;

import java.util.List;

import com.school.dao.LaboratoryRepository;
import com.school.dao.StudentRepository;
import com.school.entities.Laboratory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.entities.Student;


@RestController
@RequestMapping("/api")
public class SchoolRestController {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private LaboratoryRepository laboratoryRepository;

    @GetMapping("/students")
    public List<Student> students() {
        return studentRepository.findAll();
    }

    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable(name = "id") Long id) {
        return studentRepository.findById(id).get();
    }

    @PostMapping("/students")
    public Student save(@RequestBody Student student) {
        if (student.getLaboratory().getId() == null) {
            Laboratory laboratory = laboratoryRepository.save(student.getLaboratory());
            student.setLaboratory(laboratory);
        }
        return studentRepository.save(student);
    }

    @PutMapping("/students/{id}")
    public Student update(@PathVariable(name = "id") Long id, @RequestBody Student student) {
        student.setId(id);
        return studentRepository.save(student);
    }

    @DeleteMapping("/students/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        studentRepository.deleteById(id);
    }

}
