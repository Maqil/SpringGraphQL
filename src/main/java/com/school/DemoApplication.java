package com.school;


import com.school.dao.LaboratoryRepository;
import com.school.dao.StudentRepository;
import com.school.entities.Laboratory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;


import com.school.entities.Student;

import java.util.Date;


@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

//    @Autowired
//    private StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
//        Student s1 = studentRepository.save(new Student(null, "Boss", "Boss@gmail.com", new Date(),l2));
    }

    @Bean
    CommandLineRunner start(StudentRepository studentRepository, LaboratoryRepository laboratoryRepository, RepositoryRestConfiguration restConfiguration) {
        return args -> {
            restConfiguration.exposeIdsFor(Student.class);

//            studentRepository.deleteAll();

            Laboratory l1 = laboratoryRepository.save(new Laboratory(null, "UM6P", "um6p@gmail.com", null));
            Laboratory l2 = laboratoryRepository.save(new Laboratory(null, "LABOMAG", "labomag@gmail.com", null));
            studentRepository.save(new Student(null, "Hoddini", "Hoddini@", new Date(), l1));
            studentRepository.save(new Student(null, "HaramBe", "HaramBe@", new Date(), l2));
            studentRepository.save(new Student(null, "Kobe", "Kobe@", new Date(), l1));

            studentRepository.findAll().forEach(str -> {
                System.out.println(str.getName());
            });
        };
    }
}
