package com.project.service.impl;

import com.project.dto.VaccineDTO;
import com.project.entity.Vaccine;
import com.project.repository.VaccineRepository;
import com.project.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccineServiceImpl implements VaccineService {

    @Autowired
    private VaccineRepository vaccineRepository;

    @Override
    public List<Vaccine> getAllVaccine() {
        return vaccineRepository.getAllVaccine();
    }

    //Phuc create
    @Override
    public Vaccine findById(Integer id) {
        return vaccineRepository.getOne(id);
    }



    /**
     * Phuoc: Phân trang + tìm kiếm vắc-xin
     **/
    @Override
    public Page<Vaccine> findAllByNameContainingAndVaccineType_NameContainingAndOriginContaining(String name, String vaccineTypeName, String origin, Pageable pageable) {
        return vaccineRepository.findAllByNameContainingAndVaccineType_NameContainingAndOriginContaining(name,vaccineTypeName,origin,pageable);
    }

    /**
     * Phuoc: Tìm kiếm vắc-xin theo ID
     **/
    @Override
    public VaccineDTO getVaccineById(Integer id) {
        return vaccineRepository.getVaccineById(id);
    }

}
