package com.project.service.impl;

import com.project.dto.PeriodicVaccinationDto;
import com.project.dto.PeriodicalVaccinationRegisterDTO;
import com.project.dto.RegistrablePeriodicalVaccinationDTO;
import com.project.entity.Vaccination;
import com.project.repository.VaccinationRepository;
import com.project.service.VaccinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VaccinationServiceImpl implements VaccinationService {
    @Autowired
    private VaccinationRepository vaccinationRepository;

    //luyen code
    @Override
    public List<PeriodicVaccinationDto> findAllPeriodicVaccinations() {
        return vaccinationRepository.findAllPeriodicVaccinations();
    }

    // luyen code
    @Override
    public List<PeriodicVaccinationDto> searchPeriodicVaccinations(String name, Boolean status) {
        return vaccinationRepository.searchPeriodicVaccinations(name,status);
    }

    /*KhoaTA
     *find all periodical vaccination with date > date.now()
     */
    @Override
    public List<RegistrablePeriodicalVaccinationDTO> findAllRegistrableVaccination() {
        return this.vaccinationRepository.findAllRegistrableVaccination();
    }

    @Override
    public RegistrablePeriodicalVaccinationDTO findRegistrableVaccinationById(Integer id) {
        return this.vaccinationRepository.findRegistrableVaccinationById(id);
    }

    @Override
    public void save(PeriodicalVaccinationRegisterDTO register) {

    }


    /**
     * Phuoc: Tạo mới lịch tiêm theo yêu cầu
     **/
    @Override
    public Vaccination registerVaccination(Vaccination vaccinationTemp) {
        return vaccinationRepository.save(vaccinationTemp);
    }
}
