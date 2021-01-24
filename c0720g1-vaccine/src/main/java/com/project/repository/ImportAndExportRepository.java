package com.project.repository;

import com.project.entity.ImportAndExport;

import com.project.entity.VaccineType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ImportAndExportRepository extends JpaRepository<ImportAndExport, Integer> {

    @Query(value = "select import_and_export_id from import_and_export where import_and_export_id = ?;", nativeQuery = true)
    ImportAndExport findById(int id);

    Page<ImportAndExport> findAllByAction(String action, Pageable pageable);

    Page<ImportAndExport> findAllByActionAndStorage_Vaccine_VaccineIdAndStorage_Vaccine_OriginContainingAndStorage_Vaccine_VaccineTypeName(String action, Integer storage_vaccineId, String storage_vaccine_origin, String storage_vaccine_vaccineType_name, Pageable pageable);


}

