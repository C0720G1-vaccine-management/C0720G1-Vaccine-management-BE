package com.project.service.impl;

import com.project.dto.VaccinationHistoryFeedbackDTO;
import com.project.dto.VaccinationHistoryGetPreStatusDTO;
import com.project.dto.VaccinationHistorySendFeedbackDTO;
import com.project.entity.VaccinationHistory;
import com.project.repository.VaccinationHistoryRepository;
import com.project.service.VaccinationHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccinationHistoryServiceImpl implements VaccinationHistoryService {

    @Autowired
    private VaccinationHistoryRepository vaccinationHistoryRepository;

    /**
     * tuNH
     **/
    @Override
    public Page<VaccinationHistory> getAllVaccinationHistory(String vaccineName, String vaccinationDate, Integer patientId, Pageable pageable) {
        return this.vaccinationHistoryRepository.findAllByVaccination_Vaccine_NameContainingAndVaccination_DateContainingAndPatient_PatientId(vaccineName, vaccinationDate, patientId, pageable);
    }

    /**
     * tuNH
     **/
    @Override
    public VaccinationHistoryFeedbackDTO findByIdVaccinationHistory(Integer vaccinationHistoryId) {
        return this.vaccinationHistoryRepository.findByIdVaccinationHistory(vaccinationHistoryId);
    }

    /**
     * tuNH
     **/
    @Override
    public void updateVaccinationHistory(Integer vaccinationHistoryId, VaccinationHistorySendFeedbackDTO vaccinationHistorySendFeedbackDTO) {
        this.vaccinationHistoryRepository.updateFeedbackVaccinationHistory(vaccinationHistoryId, vaccinationHistorySendFeedbackDTO.getPreStatus());
    }

    /**
     * tuNH
     **/
    @Override
    public VaccinationHistoryGetPreStatusDTO getPreStatusVaccinationHistory(Integer vaccinationHistoryId) {
        return this.vaccinationHistoryRepository.getPreStatus(vaccinationHistoryId);
    }

    /**
     * Phuoc: Tạo mới lịch tiêm theo yêu cầu
     **/
    @Override
    public VaccinationHistory registerVaccinationHistory(VaccinationHistory vaccinationHistoryTemp) {
        return vaccinationHistoryRepository.save(vaccinationHistoryTemp);
    }

    /**
     * Nguyen Van Linh: Get all email of patient to remind vaccination
     */
    @Override
    public List<String> getAllEmailToSend() {
        return vaccinationHistoryRepository.getAllEmailToSend();
    }

}
