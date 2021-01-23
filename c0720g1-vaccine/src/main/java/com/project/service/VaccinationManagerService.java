package com.project.service;

import com.project.entity.Vaccination;
import com.project.dto.VaccinationDto;

import java.util.List;

//Trung
public interface VaccinationManagerService {
    List<Vaccination> findAllVaccinationManager();

    Vaccination findById(Integer id);

    void save(VaccinationDto vaccinationDto);

    void remove(Integer id);
}
