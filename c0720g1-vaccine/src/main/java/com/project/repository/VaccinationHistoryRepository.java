package com.project.repository;

import com.project.entity.VaccinationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VaccinationHistoryRepository extends JpaRepository<VaccinationHistory , Integer> {

    @Query(value = "select vaccine_history.vaccination_history_id, patient.name , patient.date_of_birth, patient.gender , patient.address,\n" +
            "vaccine.name , vaccine_type.name , vaccination.date , vaccine_history.after_status , vaccine_history.status \n" +
            "from vaccine_history \n" +
            "inner join patient on vaccine_history.patient_id = patient.patient_id\n" +
            "inner join vaccination on vaccine_history.vaccination_id = vaccination.vaccination_id\n" +
            "inner join vaccine on vaccination.vaccine_id = vaccine.vaccine_id\n" +
            "inner join vaccine_type on vaccine.vaccine_type_id = vaccine_type.vaccine_type_id\n" +
            "where patient.patient_id = ?1 limit ?2", nativeQuery = true)
    List<VaccinationHistory> getAllVaccinationHistory(Integer patientId, Integer page);
}
