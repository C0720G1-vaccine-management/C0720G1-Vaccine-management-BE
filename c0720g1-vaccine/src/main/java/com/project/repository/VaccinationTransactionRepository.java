package com.project.repository;

import com.project.entity.VaccinationTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VaccinationTransactionRepository extends JpaRepository<VaccinationTransaction, Integer> {
    /**
     * Made by Khanh lấy list giao dịch
     */
    Page<VaccinationTransaction> findAll(Pageable pageable);

    /**
     * Made by Khanh tìm kiếm các trường trong danh sách giao dịch
     */
    Page<VaccinationTransaction> findAllByVaccinationHistory_PatientNameContainingAndVaccinationHistory_Vaccination_Vaccine_VaccineTypeNameContaining(
            String vaccinationHistory_patient_name, String vaccinationHistory_vaccination_vaccine_vaccineType_name, Pageable pageable);

}
