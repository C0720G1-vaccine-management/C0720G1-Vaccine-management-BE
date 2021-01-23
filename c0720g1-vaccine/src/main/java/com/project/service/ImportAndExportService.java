package com.project.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.project.entity.ImportAndExport;
import java.util.List;

public interface ImportAndExportService {
//    Made by Khanh
    List<ImportAndExport> findAll();

    Page<ImportAndExport> findAll(Pageable pageable);

    ImportAndExport findById(Integer id);

    void save(ImportAndExport importAndExport);


//    Page<ImportAndExport> findByNameContaining(Pageable pageable, String name);

// Phuc
//     ImportAndExport findById(Integer id);
}
