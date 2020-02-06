package com.school.service;

import com.school.dao.LaboratoryRepository;
import com.school.dao.StudentRepository;
import com.school.entities.Laboratory;
import com.school.entities.Student;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.stream.Stream;

@Service
public class GraphQLService {
    @Value("classpath:students.graphqls")
    Resource resource;

    private GraphQL graphQL;
    @Autowired
    private AllStudentsDataFetcher allStudentsDataFetcher;
    @Autowired
    private StudentDataFetcher studentDataFetcher;
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private LaboratoryRepository laboratoryRepository;

    // load schema at application start up
    @PostConstruct
    private void loadSchema() throws IOException {


        loadDataIntoHSQL();


        File schemaFile = resource.getFile();


        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private void loadDataIntoHSQL() {

        Laboratory l1 = laboratoryRepository.save(new Laboratory(null, "UM6P", "um6p@gmail.com", null));
        Laboratory l2 = laboratoryRepository.save(new Laboratory(null, "LABOMAG", "labomag@gmail.com", null));

        Stream.of(
                new Student(null, "michael", "Hoddini@", new Date(), l1),
                new Student(null, "James", "HaramBe@", new Date(), l2),
                new Student(null, "Bron", "Kobe@", new Date(), l1)
        ).forEach(student -> {
            studentRepository.save(student);
        });
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("allStudents", allStudentsDataFetcher)
                        .dataFetcher("student", studentDataFetcher))
                .build();
    }

    public GraphQL getGraphQL() {
        return graphQL;
    }
}
