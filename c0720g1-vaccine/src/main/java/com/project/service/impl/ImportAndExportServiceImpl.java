package com.project.service.impl;
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
    public List<ImportAndExport> findAll() {
        List<ImportAndExport> list = this.importAndExportRepository.findAll();
        List<ImportAndExport> list2 = new ArrayList<>();
        for (ImportAndExport item: list) {
            if (item.getAction().equals("export")){
                list2.add(item);
            }
        }
        return list2;
    }

    @Override
    public Page<ImportAndExport> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public ImportAndExport findById(Integer id) {
        return this.importAndExportRepository.findById(id).orElse(null);
    }

    @Override
    public void save(ImportAndExport importAndExport) {
        this.importAndExportRepository.save(importAndExport);
    }

}
