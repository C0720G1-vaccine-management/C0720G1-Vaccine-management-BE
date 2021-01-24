package com.project.service;

import com.project.entity.VaccinationTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VaccinationTransactionService {


    Page<VaccinationTransaction> findAll(Pageable pageable);
    Page<VaccinationTransaction> search(String patientName, String vaccineType,Pageable pageable);

    void edit(VaccinationTransaction vaccinationTransaction);


    void save(VaccinationTransaction vaccinationTransaction);

    void delete(Integer id);
}
