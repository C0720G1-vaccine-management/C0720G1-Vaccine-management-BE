package com.project.service;

import com.project.dto.*;
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
//    List<RegistrablePeriodicalVaccinationDTO> findAllRegistrableVaccination();

    /*KhoaTA
     *Find registrable vaccination by Id
     */
    RegistrablePeriodicalVaccinationDTO findRegistrableVaccinationById(Integer id);

    /*KhoaTA
     *Find registrable vaccination by Id
     */
    void saveRegister(PeriodicalVaccinationRegisterDTO register);


    /**
     * Phuoc: Tạo mới lịch tiêm theo yêu cầu
     **/
    Vaccination registerVaccination(Vaccination vaccinationTemp);

    /*KhoaTA
     *get the list of all vaccine's ages
     */
    List<String> findAllVaccineAge();

    /*KhoaTA
     *get the list of all vaccine's ages
     */
    List<TimeDTO> findAllVaccinationTime();
    /*KhoaTA
     *get the total page of search data
     */
    double getTotalPage(PeriodicalSearchDataDTO searchData);

    List<RegistrablePeriodicalVaccinationDTO> findCustomVaccination(PeriodicalSearchDataDTO searchData);
}
