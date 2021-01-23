package com.project.service.impl;

import com.project.entity.Vaccination;
import com.project.dto.VaccinationDto;
import com.project.repository.LocationRepository;
import com.project.repository.VaccinationManagerRepository;
import com.project.repository.VaccinationTypeRepository;
import com.project.repository.VaccineRepository;
import com.project.service.VaccinationManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//Trung
@Service
public class VaccinationManagerServiceImpl implements VaccinationManagerService {
    @Autowired
    private VaccinationManagerRepository vaccinationManagerRepository;

    @Override
    public List<Vaccination> findAllVaccinationManager() {
        return vaccinationManagerRepository.findAlLVaccinationManager(1,1);
    }

    @Override
    public Vaccination findById(Integer id) {
        return vaccinationManagerRepository.findByIdVaccination(id, 1,1);
    }

    @Override
    public void save(VaccinationDto vaccinationDto) {
        vaccinationManagerRepository.saveVaccinationManager(vaccinationDto.getDate(), true, vaccinationDto.getDescription(), vaccinationDto.getStartTime(), vaccinationDto.getEndTime(), vaccinationDto.getStatus(), vaccinationDto.getLocationId(), true, vaccinationDto.getVaccineId());
    }

    @Override
    public void remove(Integer id) {
        vaccinationManagerRepository.deleteById(id);
    }
}