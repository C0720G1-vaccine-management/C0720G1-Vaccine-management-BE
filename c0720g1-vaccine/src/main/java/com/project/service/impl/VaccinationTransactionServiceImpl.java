package com.project.service.impl;

import com.project.entity.VaccinationTransaction;
import com.project.repository.VaccinationTransactionRepository;
import com.project.service.VaccinationTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccinationTransactionServiceImpl implements VaccinationTransactionService {
    @Autowired
    VaccinationTransactionRepository vaccinationTransactionRepository;

    @Override
    public Page<VaccinationTransaction> findAll(Pageable pageable) {
        return vaccinationTransactionRepository.findAll(pageable);
    }

    @Override
    public Page<VaccinationTransaction> search(String patientName, String vaccineType, Pageable pageable) {
        return vaccinationTransactionRepository.findAllByVaccinationHistory_PatientNameContainingAndVaccinationHistory_Vaccination_Vaccine_VaccineTypeName(patientName,vaccineType,pageable);
    }

    @Override
    public void edit(VaccinationTransaction vaccinationTransaction) {
        this.vaccinationTransactionRepository.save(vaccinationTransaction);
    }


    @Override
    public void save(VaccinationTransaction vaccinationTransaction) {
        this.vaccinationTransactionRepository.save(vaccinationTransaction);
    }

    @Override
    public void delete(Integer id) {
        this.vaccinationTransactionRepository.deleteById(id);
    }
}
