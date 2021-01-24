package com.project.service;
import com.project.dto.ImportAndExportDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.project.entity.ImportAndExport;
import java.util.List;

public interface ImportAndExportService {
    //    Made by Khanh
    Page<ImportAndExport> findAll(String action,Pageable pageable);

    Page<ImportAndExport> search(String action,Integer vaccineId,String vaccineType,String origin,Pageable pageable);

    ImportAndExport findById(Integer id);

    void save(ImportAndExportDTO importAndExportDTO);


//    Page<ImportAndExport> findByNameContaining(Pageable pageable, String name);

// Phuc
//     ImportAndExport findById(Integer id);
}
