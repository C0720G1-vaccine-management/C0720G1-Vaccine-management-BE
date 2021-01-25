package com.project.repository;


import com.project.dto.VaccinationHistoryFeedbackDTO;
import com.project.dto.VaccinationHistoryGetPreStatusDTO;
import com.project.entity.VaccinationHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface VaccinationHistoryRepository extends JpaRepository<VaccinationHistory, Integer> {

    /**
     * tuNH
     **/
    Page<VaccinationHistory> findAllByVaccination_Vaccine_NameContainingAndVaccination_DateContainingAndPatient_PatientId(String vaccination_vaccine_name, String vaccination_date, Integer patient_patientId, Pageable pageable);

    /**
     * tuNH
     **/
    @Query(value = "select patient.patient_id as patientId, patient.name as patientName, \n" +
            "patient.gender as patientGender, patient.date_of_birth as patientAge, \n" +
            "patient.guardian as patientGuardian, patient.address as patientAddress, \n" +
            "patient.phone as patientPhone,vaccine_type.name as vaccineTypeName,\n" +
            "vaccination.date as vaccinationDate, vaccine_history.pre_status as vaccineHistoryPreStatus\n" +
            "from vaccine_history\n" +
            "inner join patient on vaccine_history.patient_id = patient.patient_id\n" +
            "inner join vaccination on vaccine_history.vaccination_id = vaccination.vaccination_id\n" +
            "inner join vaccine on vaccination.vaccine_id = vaccine.vaccine_id\n" +
            "inner join vaccine_type on vaccine.vaccine_type_id = vaccine_type.vaccine_type_id\n" +
            "where vaccine_history.vaccination_history_id = ?1 ", nativeQuery = true)
    VaccinationHistoryFeedbackDTO findByIdVaccinationHistory(Integer vaccinationHistoryId);

    /**
     * tuNH
     **/
    @Modifying
    @Transactional
    @Query(value = "UPDATE vaccine_history" +
            " SET pre_status = ?2" +
            " WHERE vaccination_history_id = ?1", nativeQuery = true)
    void updateFeedbackVaccinationHistory(Integer vaccinationHistoryId, String vaccinationHistoryPreStatus);

    /**
     * tuNH
     **/
    @Query(value = "select pre_status as preStatus from vaccine_history where vaccination_history_id = ?1", nativeQuery = true)
    VaccinationHistoryGetPreStatusDTO getPreStatus(Integer vaccinationHistoryId);

}