package com.project.service.impl;

import com.project.entity.Vaccine;
import com.project.repository.VaccineRepository;
import com.project.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
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

}
