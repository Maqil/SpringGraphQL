package com.school;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.school.dao.StudentRepository;
import com.school.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Query implements GraphQLQueryResolver {
    @Autowired
    StudentRepository studentRepository;

    public Student student(int id) {
        return studentRepository.findById((long) id).get();
    }

//    public List<Student> allStudent() {
////        return null;
////    }
}
