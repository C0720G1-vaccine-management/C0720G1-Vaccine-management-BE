package com.project.repository;

import com.project.dto.PeriodicVaccinationDto;
import com.project.dto.RegistrablePeriodicalVaccinationDTO;
import com.project.entity.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VaccinationRepository extends JpaRepository<Vaccination,Integer> {



    //luyen code
    @Query(value = "select patient.patient_id as patientId ,name as name,date_of_birth as dateOfBirth,gender as gender,guardian as guardian,phone as phone,address as address,vaccination.status as status from patient" +
            " join vaccine_history on patient.patient_id = vaccine_history.patient_id" +
            " join vaccination on vaccine_history.vaccination_id = vaccination.vaccination_id",
            nativeQuery = true)
    List<PeriodicVaccinationDto> findAllPeriodicVaccinations();

    // luyen code
    @Query(value = "select patient.patient_id as patientId ,name as name,date_of_birth as dateOfBirth,gender as gender,guardian as guardian,phone as phone,address as address,vaccination.status as status from patient" +
            " join vaccine_history on patient.patient_id = vaccine_history.patient_id" +
                    " join vaccination on vaccine_history.vaccination_id = vaccination.vaccination_id where vaccination.status = b'0'",nativeQuery = true)
    List<PeriodicVaccinationDto> searchPeriodicVaccinations(String name,Boolean statusCm);

    /*KhoaTA
     *find all periodical vaccination with date > date.now()
     */
    @Query(value = "select vaccination.vaccination_id as vaccinationId, vaccination.date, vaccination.start_time as startTime, vaccination.end_time as endTime, vaccine.name as vaccineName, vaccine_type.name as vaccineTypeName, vaccine.age as age, vaccination.description, location.name as location, vaccine.origin as country from vaccination " +
            "join vaccination_type on vaccination.vaccination_type_id = vaccination_type.vaccination_type_id " +
            "join vaccine on vaccination.vaccine_id = vaccine.vaccine_id " +
            "join location on vaccination.location_id = location.location_id " +
            "join vaccine_type on vaccine.vaccine_type_id = vaccine_type.vaccine_type_id " +
            "where vaccination.date > now() and vaccination.vaccination_type_id = 1 LIMIT 1,5", nativeQuery = true)
    List<RegistrablePeriodicalVaccinationDTO> findAllRegistrableVaccination();
    /*KhoaTA
     *find periodical vaccination with date > date.now() and id = argument
     */
    @Query(value = "select vaccination.vaccination_id as vaccinationId, vaccination.date, vaccination.start_time as startTime, vaccination.end_time as endTime, vaccine.name as vaccineName, vaccine_type.name as vaccineTypeName, vaccine.age as age, vaccination.description, location.name as location, vaccine.origin as country from vaccination " +
            "join vaccination_type on vaccination.vaccination_type_id = vaccination_type.vaccination_type_id " +
            "join vaccine on vaccination.vaccine_id = vaccine.vaccine_id " +
            "join location on vaccination.location_id = location.location_id " +
            "join vaccine_type on vaccine.vaccine_type_id = vaccine_type.vaccine_type_id " +
            "where vaccination.date > now() and vaccination.vaccination_type_id = 1 and vaccination.vaccination_id = ?1 ", nativeQuery = true)
    RegistrablePeriodicalVaccinationDTO findRegistrableVaccinationById(Integer id);
}
