package com.project.dto;

import com.project.entity.Patient;
import com.project.entity.Vaccination;
import com.project.entity.Vaccine;
import com.project.entity.VaccineType;
import lombok.Data;

@Data
public class VaccinationHistoryDTO {
    private Integer vaccinationHistoryId;
    private Boolean status;
    private Double dosage;
    private String preStatus;
    private String afterStatus;
    private Vaccination vaccination;
    private Patient patient;
    private VaccineType vaccineType;
    protected Vaccine vaccine;
}
