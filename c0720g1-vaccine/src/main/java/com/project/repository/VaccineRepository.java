package com.project.repository;
import java.util.List;

import com.project.dto.VaccineDTO;
import com.project.entity.Vaccine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface VaccineRepository extends JpaRepository<Vaccine,Integer> {
  
    @Query(value = "select * from vaccine_management.vaccine", nativeQuery = true)
    List<Vaccine> getAllVaccine();
  
    //Phuc create
    @Query(value = "select * from vaccine where vaccine_id = ?1", nativeQuery = true)
    Vaccine findByVaccineId(Integer id);


    /**
     * Phuoc: Phân trang + tìm kiếm vắc-xin
     **/
    Page<Vaccine> findAllByNameContainingAndVaccineType_NameContainingAndOriginContaining(String name, String vaccineType_name, String origin, Pageable pageable);


    /**
     * Phuoc: Tìm kiếm vắc-xin theo ID
     **/
    @Query(value = "select vaccine.vaccine_id as vaccineId, vaccine.name as name, vaccine_type.name as vaccineTypeName, vaccine.origin as origin from vaccine " +
            "join vaccine_type on vaccine.vaccine_type_id = vaccine_type.vaccine_type_id " +
            "where vaccine.vaccine_id = ?1", nativeQuery = true)
    VaccineDTO getVaccineById(Integer id);

}

