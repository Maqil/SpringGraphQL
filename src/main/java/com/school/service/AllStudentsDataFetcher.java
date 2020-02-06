package com.school.service;

import com.school.dao.StudentRepository;
import com.school.entities.Student;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllStudentsDataFetcher implements DataFetcher<List<Student>> {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public List<Student> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return studentRepository.findAll();
    }
}
