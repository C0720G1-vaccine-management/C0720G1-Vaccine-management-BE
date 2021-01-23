package com.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "vaccination_type")
public class VaccinationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vaccinationTypeId;
    private String name;
    @OneToMany(mappedBy = "vaccinationType")
    private Set<Vaccination>vaccinationList;
}
