package com.project.service;

import com.project.dto.VaccinationHistoryFeedbackDTO;
import com.project.dto.VaccinationHistoryGetAfterStatusDTO;
import com.project.dto.VaccinationHistoryRegisteredDTO;
import com.project.dto.VaccinationHistorySendFeedbackDTO;
import com.project.entity.VaccinationHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface VaccinationHistoryService {
    /**
     * tuNH
     **/
    Page<VaccinationHistory> getAllVaccinationHistory(String vaccineName, String vaccinationDate, String accountEmail, Pageable pageable);

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
     * Made by Khanh lấy list history
     */
    List<VaccinationHistory> findAll();

     /**
     * Nguyen Van Linh: Get all email of patient to remind vaccination
     */

    List<String> getAllEmailToSend();

    /**
     * Nguyen Van Linh: Get all email of patient to remind vaccination with one more time they vaccination
     */
    List<String> getEmailToSendOfVaccinationMore();


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
    Page<VaccinationHistory> searchNoStatusPeriodicVaccination(String name,Pageable pageable);

    /** LuyenNT code
     */
    Page<VaccinationHistory> finAllPeriodicVaccination(Pageable pageable);
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

    /**
     * KhoaTA
     * Cancel periodical vaccination register
     */
    void cancelRegister(int vaccinationId, int patientId);

    /**
     TuNH:  sendMailFeedbackForAdmin
     **/
    void sendMailFeedbackForAdmin(String value, String accountEmail) throws MessagingException, UnsupportedEncodingException;

}
