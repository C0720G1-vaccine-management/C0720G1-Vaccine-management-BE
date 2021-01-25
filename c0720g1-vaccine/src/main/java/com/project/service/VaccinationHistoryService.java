package com.project.service;



import com.project.dto.VaccinationHistoryDTO;
import com.project.dto.VaccinationHistoryFeedbackDTO;
import com.project.dto.VaccinationHistoryRegisteredDTO;
import com.project.dto.VaccinationHistorySendFeedbackDTO;
import com.project.entity.VaccinationHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VaccinationHistoryService {
    /* tuNH */
    List<VaccinationHistoryDTO> getAllVaccinationHistory(Integer patientId);

    /* tuNH */
    VaccinationHistoryFeedbackDTO findByIdVaccinationHistory(Integer vaccinationHistoryId);

    /* tuNH */
    void updateVaccinationHistory(Integer vaccinationHistoryId, VaccinationHistorySendFeedbackDTO vaccinationHistorySendFeedbackDTO);


    /**
     * Phuoc: Tạo mới lịch tiêm theo yêu cầu
     **/
    VaccinationHistory registerVaccinationHistory(VaccinationHistory vaccinationHistoryTemp);

    /**
     * Nguyen Van Linh: Get all email of patient to remind vaccination
     */
    List<String>getAllEmailToSend();

    /**
     list:  create by LongBP
     **/
    Page<VaccinationHistory> getAllRegisteredRequired(String name, Pageable pageable);

    /**
     search and paging:  create by LongBP
     **/
    Page<VaccinationHistory> searchNameAndInjected(String name, Boolean status, Pageable pageable);

    /**
     find by id:  create by LongBP
     **/
    VaccinationHistoryRegisteredDTO findId(Integer id);
}
