package com.project.service;

import com.project.entity.VaccinationHistory;

import java.util.List;

public interface VaccinationHistoryService {
    //create by tu
    List<VaccinationHistory> getAllVaccinationHistory(Integer patientId, Integer page);
}
