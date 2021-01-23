package com.project.service;

import com.project.dto.PeriodicVaccinationDto;
import com.project.dto.PeriodicalVaccinationRegisterDTO;
import com.project.dto.RegistrablePeriodicalVaccinationDTO;
import com.project.entity.Vaccination;

import java.util.List;

public interface VaccinationService {

    //luyen code
    List<PeriodicVaccinationDto> findAllPeriodicVaccinations();

    // luyen code
    List<PeriodicVaccinationDto> searchPeriodicVaccinations(String name,Boolean status);

    /*KhoaTA
     *find all periodical vaccination with date > date.now()
     */
    List<RegistrablePeriodicalVaccinationDTO> findAllRegistrableVaccination();

    /*KhoaTA
     *Find registrable vaccination by Id
     */
    RegistrablePeriodicalVaccinationDTO findRegistrableVaccinationById(Integer id);

    /*KhoaTA
     *Find registrable vaccination by Id
     */
    void save(PeriodicalVaccinationRegisterDTO register);


    /**
     * Phuoc: Tạo mới lịch tiêm theo yêu cầu
     **/
    Vaccination registerVaccination(Vaccination vaccinationTemp);
}
