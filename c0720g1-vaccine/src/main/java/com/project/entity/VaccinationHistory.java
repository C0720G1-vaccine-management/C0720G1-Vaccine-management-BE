package com.project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "vaccination_history")
public class VaccinationHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vaccinationHistoryId;
    private Boolean status;
    private Double dosage;
    private String preStatus;
    private String afterStatus;
    @ManyToOne
    @JoinColumn(name = "vaccination_id",nullable = false)
    private Vaccination vaccination;

    @ManyToOne
    @JoinColumn(name = "patient_id",nullable = false)
    private Patient patient;

    @OneToOne(mappedBy = "vaccinationHistory")
    @JsonBackReference
    private VaccinationTransaction vaccinationTransaction;
}
