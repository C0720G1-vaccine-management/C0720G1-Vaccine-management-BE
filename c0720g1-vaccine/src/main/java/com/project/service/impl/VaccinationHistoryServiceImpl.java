package com.project.service.impl;


import com.project.dto.VaccinationHistoryDTO;
import com.project.dto.VaccinationHistoryFeedbackDTO;
import com.project.dto.VaccinationHistorySendFeedbackDTO;
import com.project.entity.VaccinationHistory;
import com.project.repository.VaccinationHistoryRepository;
import com.project.service.VaccinationHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccinationHistoryServiceImpl implements VaccinationHistoryService {
    @Autowired
    private VaccinationHistoryRepository vaccinationHistoryRepository;

    /* tuNH */
    @Override
    public List<VaccinationHistoryDTO> getAllVaccinationHistory(Integer patientId) {
        return this.vaccinationHistoryRepository.getAllVaccinationHistory(patientId);
    }

    /* tuNH */
    @Override
    public VaccinationHistoryFeedbackDTO findByIdVaccinationHistory(Integer vaccinationHistoryId) {
        return this.vaccinationHistoryRepository.findByIdVaccinationHistory(vaccinationHistoryId);
    }

    /* tuNH */
    @Override
    public void updateVaccinationHistory(Integer vaccinationHistoryId, VaccinationHistorySendFeedbackDTO vaccinationHistorySendFeedbackDTO) {
        this.vaccinationHistoryRepository.updateFeedbackVaccinationHistory(vaccinationHistoryId,vaccinationHistorySendFeedbackDTO.getPreStatus());
    }

    /**
     * Phuoc: Tạo mới lịch tiêm theo yêu cầu
     **/
    @Override
    public VaccinationHistory registerVaccinationHistory(VaccinationHistory vaccinationHistoryTemp) {
        return vaccinationHistoryRepository.save(vaccinationHistoryTemp);
    }
}
