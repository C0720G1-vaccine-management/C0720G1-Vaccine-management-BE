package com.project.repository;

import com.project.entity.VaccinationTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VaccinationTransactionRepository extends JpaRepository<VaccinationTransaction,Integer> {

}
