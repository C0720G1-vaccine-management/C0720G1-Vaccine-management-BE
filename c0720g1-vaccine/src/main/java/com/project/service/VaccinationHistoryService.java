package com.project.service;

import com.project.dto.VaccinationHistoryFeedbackDTO;
import com.project.dto.VaccinationHistoryGetPreStatusDTO;
import com.project.dto.VaccinationHistorySendFeedbackDTO;
import com.project.entity.VaccinationHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VaccinationHistoryService {
    /**
     * tuNH
     **/
    Page<VaccinationHistory> getAllVaccinationHistory(String vaccineName, String vaccinationDate, Integer patientId, Pageable pageable);

    /**
     * tuNH
     **/
    VaccinationHistoryFeedbackDTO findByIdVaccinationHistory(Integer vaccinationHistoryId);

    /**
     * tuNH
     **/
    void updateVaccinationHistory(Integer vaccinationHistoryId, VaccinationHistorySendFeedbackDTO vaccinationHistorySendFeedbackDTO);

    /**
     * tuNH
     **/
    VaccinationHistoryGetPreStatusDTO getPreStatusVaccinationHistory(Integer vaccinationHistoryId);

}
