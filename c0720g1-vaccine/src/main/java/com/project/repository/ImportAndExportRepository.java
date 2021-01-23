package com.project.repository;
import com.project.entity.ImportAndExport;
import com.project.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ImportAndExportRepository extends JpaRepository<ImportAndExport, Integer> {
}
