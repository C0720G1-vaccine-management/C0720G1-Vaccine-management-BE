package com.project.repository;

import com.project.entity.VaccinationTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VaccinationTransactionRepository extends JpaRepository<VaccinationTransaction,Integer> {

    Page<VaccinationTransaction> findAll(Pageable pageable);

    Page<VaccinationTransaction> findAllByVaccinationHistory_PatientNameContainingAndVaccinationHistory_Vaccination_Vaccine_VaccineTypeName(
            String vaccinationHistory_patient_name, String vaccinationHistory_vaccination_vaccine_vaccineType_name, Pageable pageable);


}
