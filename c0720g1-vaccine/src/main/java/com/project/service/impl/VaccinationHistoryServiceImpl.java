package com.project.service.impl;


import com.project.dto.VaccinationHistoryDTO;
import com.project.dto.VaccinationHistoryFeedbackDTO;
import com.project.dto.VaccinationHistoryRegisteredDTO;
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

    /**
     * Nguyen Van Linh: Get all email of patient to remind vaccination
     */
    @Override
    public List<String> getAllEmailToSend() {
        return vaccinationHistoryRepository.getAllEmailToSend();
    }

    /**
     * LuyenNT
     *
     * @param name
     * @param status
     * @return
     */
    @Override
    public Page<VaccinationHistory> searchPeriodicVaccination(String name, Boolean status, Pageable pageable) {
        return vaccinationHistoryRepository.findAllByPatient_NameContainingAndVaccination_StatusIs(name, status, pageable);
    }

    /**
     * LuyenNT code
     *
     * @param name
     * @param pageable
     * @return
     */
    @Override
    public Page<VaccinationHistory> finAllPeriodicVaccination(String name, Pageable pageable) {
        return vaccinationHistoryRepository.findAllByPatient_NameContaining(name, pageable);

    /**
     *list:  create by LongBP
     */
    @Override
    public Page<VaccinationHistory> getAllRegisteredRequired(String name, Pageable pageable) {
        return this.vaccinationHistoryRepository.findAllByPatient_NameContaining(name, pageable);
    }

    /**
     search and paging:  create by LongBP
     **/
    @Override
    public Page<VaccinationHistory> searchNameAndInjected(String name, Boolean status, Pageable pageable) {
        return this.vaccinationHistoryRepository.findAllByPatient_NameContainingAndStatusIs(name, status, pageable);
    }

    /**
     find by id:  create by LongBP
     **/
    @Override
    public VaccinationHistoryRegisteredDTO findId(Integer id) {
        return this.vaccinationHistoryRepository.findId(id);
    }
}
