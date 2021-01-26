package com.project.service;

import com.project.dto.VaccinationHistoryFeedbackDTO;
import com.project.dto.VaccinationHistoryGetAfterStatusDTO;
import com.project.dto.VaccinationHistoryRegisteredDTO;
import com.project.dto.VaccinationHistorySendFeedbackDTO;
import com.project.entity.VaccinationHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

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
    VaccinationHistoryGetAfterStatusDTO getAfterStatusVaccinationHistory(Integer vaccinationHistoryId);

    /**
     * Phuoc: Tạo mới lịch tiêm theo yêu cầu
     **/
    VaccinationHistory registerVaccinationHistory(VaccinationHistory vaccinationHistoryTemp);

    /**
     * Nguyen Van Linh: Get all email of patient to remind vaccination
     */

    List<String> getAllEmailToSend();



    /** LuyenNT code
     *
     * @param name
     * @param status
     * @return
     */
    Page<VaccinationHistory> searchPeriodicVaccination(String name, Boolean status, Pageable pageable);

    /** LuyenNT code
     * @param name
     * @param pageable
     * @return
     */
    Page<VaccinationHistory> finAllPeriodicVaccination(String name, Pageable pageable);

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
