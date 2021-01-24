package com.project.service;
import java.util.List;

import com.project.dto.VaccineDTO;
import com.project.entity.Vaccine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VaccineService {
    List<Vaccine> getAllVaccine();
        //Phuc create
    Vaccine findById(Integer id);

    /**
     * Phuoc: Phần trang + Tìm kiếm vắc-xin
    **/
    Page<Vaccine> findAllByNameContainingAndVaccineType_NameContainingAndOriginContaining(String name, String vaccineTypeName, String origin, Pageable pageable);

    /**
     * Phuoc: Phần trang + Tìm kiếm vắc-xin
     **/
    Page<Vaccine> findAllByQuantityIsExits(String name, String vaccineType_name, String origin, Pageable pageable);

    /**
     * Phuoc: Phần trang + Tìm kiếm vắc-xin
     **/
    Page<Vaccine> findAllByQuantityIsNotExits(String name, String vaccineType_name, String origin, Pageable pageable);


    /**
     * Phuoc: Tìm kiếm vắc-xin theo ID
     **/
    VaccineDTO getVaccineById(Integer id);
}
