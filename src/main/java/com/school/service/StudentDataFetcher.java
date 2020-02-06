package com.school.service;

import com.school.dao.StudentRepository;
import com.school.entities.Student;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentDataFetcher implements DataFetcher<Student> {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public Student get(DataFetchingEnvironment dataFetchingEnvironment) {
        Long id = dataFetchingEnvironment.getArgument("id");
        return studentRepository.findById((long) id).get();
    }
}
