package com.project.service.impl;

import com.project.entity.VaccinationHistory;
import com.project.repository.VaccinationHistoryRepository;
import com.project.service.VaccinationHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccinationHistoryServiceImpl implements VaccinationHistoryService {
    @Autowired
    private VaccinationHistoryRepository repository;

    @Override
    public List<VaccinationHistory> getAllVaccinationHistory(Integer patientId, Integer page) {
        return repository.getAllVaccinationHistory(patientId, page);
    }
}
