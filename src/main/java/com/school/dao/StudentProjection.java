package com.school.dao;

import org.springframework.data.rest.core.config.Projection;

import com.school.entities.Student;

@Projection(name = "p1", types = Student.class)
public interface StudentProjection {
    public String getName();

    public String getEmail();
}
