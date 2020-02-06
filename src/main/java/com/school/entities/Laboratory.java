package com.school.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Laboratory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Size(min = 2, max = 20)
    private String name;
    private String contact;
    @OneToMany(mappedBy = "laboratory")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Student> students;

}
