package com.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "vaccination")
public class Vaccination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vaccinationId;
    @Column(columnDefinition = "Time")
    private String startTime;
    @Column(columnDefinition = "Time")
    private String endTime;
    @Column(columnDefinition = "Date")
    private String date;
    private Boolean status;
    private String description;
    private Boolean deleteFlag;
    @ManyToOne
    @JoinColumn(name = "vaccine_id",nullable = false)
    private Vaccine vaccine;
    @OneToMany(mappedBy = "vaccination")
    private Set<VaccinationHistory>vaccinationHistoryList;
    @ManyToOne
    @JoinColumn(name = "vaccination_type_id",nullable = false)
    private VaccinationType vaccinationType;
    @ManyToOne
    @JoinColumn(name = "location_id",nullable = false)
    private Location location;
}
