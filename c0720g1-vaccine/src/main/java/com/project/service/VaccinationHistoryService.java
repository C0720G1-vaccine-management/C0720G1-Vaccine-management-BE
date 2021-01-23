package com.project.service;



import com.project.dto.VaccinationHistoryDTO;
import com.project.dto.VaccinationHistoryFeedbackDTO;
import com.project.dto.VaccinationHistorySendFeedbackDTO;
import com.project.entity.VaccinationHistory;

import java.util.List;

public interface VaccinationHistoryService {
    /* tuNH */
    List<VaccinationHistoryDTO> getAllVaccinationHistory(Integer patientId);

    /* tuNH */
    VaccinationHistoryFeedbackDTO findByIdVaccinationHistory(Integer vaccinationHistoryId);

    /* tuNH */
    void updateVaccinationHistory(Integer vaccinationHistoryId, VaccinationHistorySendFeedbackDTO vaccinationHistorySendFeedbackDTO);
}
