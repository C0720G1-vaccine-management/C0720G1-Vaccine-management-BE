package com.project.service.impl;
import com.project.dto.ImportAndExportDTO;
import org.springframework.stereotype.Service;
import com.project.entity.ImportAndExport;
import com.project.repository.ImportAndExportRepository;
import com.project.service.ImportAndExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImportAndExportServiceImpl implements ImportAndExportService {

    @Autowired
    private ImportAndExportRepository importAndExportRepository;
    //Made by Khanh
    @Override
    public Page<ImportAndExport> findAll(String action,Pageable pageable) {
        return this.importAndExportRepository.findAllByAction(action, pageable);
    }

    @Override
    public Page<ImportAndExport> search(String action,Integer vaccineId,String vaccineType,String origin,Pageable pageable) {
        return this.importAndExportRepository.findAllByActionAndStorage_Vaccine_VaccineIdAndStorage_Vaccine_OriginContainingAndStorage_Vaccine_VaccineTypeName(action,vaccineId,vaccineType,origin,pageable);
    }

    @Override
    public ImportAndExport findById(Integer id) {
        return this.importAndExportRepository.findById(id).orElse(null);
    }

    @Override
    public void save(ImportAndExportDTO importAndExportDTO) {
        ImportAndExport importAndExport = findById(  importAndExportDTO.getImportAndExportId());
        importAndExport.setPrice(importAndExportDTO.getPrice());
        importAndExport.setDate(importAndExportDTO.getDate());
        importAndExport.setAction(importAndExportDTO.getAction());
        importAndExport.setDeleteFlag(importAndExportDTO.getDeleteFlag());
        importAndExport.setQuantity(importAndExportDTO.getQuantity());
        this.importAndExportRepository.save(importAndExport);
    }

}
