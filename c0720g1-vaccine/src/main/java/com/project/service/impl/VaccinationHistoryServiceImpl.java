package com.project.service.impl;

import com.project.dto.VaccinationHistoryFeedbackDTO;
import com.project.dto.VaccinationHistoryGetAfterStatusDTO;
import com.project.dto.VaccinationHistoryRegisteredDTO;
import com.project.dto.VaccinationHistorySendFeedbackDTO;
import com.project.entity.VaccinationHistory;
import com.project.repository.VaccinationHistoryRepository;
import com.project.service.VaccinationHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public class VaccinationHistoryServiceImpl implements VaccinationHistoryService {

    @Autowired
    private VaccinationHistoryRepository vaccinationHistoryRepository;

    @Autowired
    JavaMailSender javaMailSender;

    /**
     * tuNH
     **/
    @Override
    public Page<VaccinationHistory> getAllVaccinationHistory(String vaccineName, String vaccinationDate, String accountEmail, Pageable pageable) {
        return this.vaccinationHistoryRepository.findAllByVaccination_Vaccine_NameContainingAndVaccination_DateContainingAndPatient_Email(vaccineName, vaccinationDate, accountEmail, pageable);
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
        this.vaccinationHistoryRepository.updateFeedbackVaccinationHistory(vaccinationHistoryId, vaccinationHistorySendFeedbackDTO.getAfterStatus());
    }

    /**
     * tuNH
     **/
    @Override
    public VaccinationHistoryGetAfterStatusDTO getAfterStatusVaccinationHistory(Integer vaccinationHistoryId) {
        return this.vaccinationHistoryRepository.getAfterStatus(vaccinationHistoryId);
    }

    /**
     * Phuoc: Tạo mới lịch tiêm theo yêu cầu
     **/
    @Override
    public VaccinationHistory registerVaccinationHistory(VaccinationHistory vaccinationHistoryTemp) {
        return vaccinationHistoryRepository.save(vaccinationHistoryTemp);
    }

    /**
     * Made by Khanh lay list history
     */
    @Override
    public List<VaccinationHistory> findAll() {
        return this.vaccinationHistoryRepository.findAll();
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
        return vaccinationHistoryRepository.findAllByPatient_NameContainingAndStatusIs(name, status, pageable);
    }

    /**
     * LuyenNT code
     *
     * @param name
     * @param pageable
     * @return
     */
    @Override
    public Page<VaccinationHistory> searchNoStatusPeriodicVaccination(String name, Pageable pageable) {
        return vaccinationHistoryRepository.findAllByPatient_NameContaining(name, pageable);
    }

    /**
     * LuyenNT
     * @param pageable
     * @return
     */
    @Override
    public Page<VaccinationHistory> finAllPeriodicVaccination(Pageable pageable) {
        return vaccinationHistoryRepository.findAll(pageable);
    }

    /**
     * list:  create by LongBP
     */
    @Override
    public Page<VaccinationHistory> getAllRegisteredRequired(String name, Pageable pageable) {
        return this.vaccinationHistoryRepository.findAllByPatient_NameContaining(name, pageable);
    }


    /**
     * search and paging:  create by LongBP
     **/
    @Override
    public Page<VaccinationHistory> searchNameAndInjected(String name, Boolean status, Pageable pageable) {
        return this.vaccinationHistoryRepository.findAllByPatient_NameContainingAndStatusIs(name, status, pageable);
    }

    /**
     * find by id:  create by LongBP
     **/
    @Override
    public List<VaccinationHistoryRegisteredDTO> findId(Integer id) {
        return this.vaccinationHistoryRepository.findId(id);
    }

    /**
     * edit by id:  create by LongBP
     **/
    @Override
    public void updateStatusVaccinationHistory(Boolean status, String preStatus, Integer id) {
        vaccinationHistoryRepository.updateVaccinationHistoryStatus(status, preStatus, id);
    }
     /** KhoaTA
     * Cancel periodical vaccination register
     */
    @Override
    public void cancelRegister(int vaccinationId, int patientId) {
        this.vaccinationHistoryRepository.cancelRegister(vaccinationId, patientId);
    }
     /**
     * TuNH:  sendMailFeedbackForAdmin
     **/
    @Override
    public void sendMailFeedbackForAdmin(String value, String accountEmail) throws MessagingException, UnsupportedEncodingException {
        String subject = "Hãy xác thực email của bạn";
        String mailContent = "";

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
        helper.setTo("nguyenhoangtu24061999@gmail.com");
        helper.setFrom("vanlinh12b5@gmail.com", "TRUNG TÂM Y TẾ DỰ PHÒNG ĐÀ NẴNG");
        helper.setSubject(subject);
        mailContent = "<p sytle='color:red;'>Phản hồi từ người dùng ,<p>"
                + "<p sytle='color:red;'>Nội dung:" + value + "<p>"
                + "<p sytle='color:red;'> Bạn có một email phản hồi từ " + accountEmail + "<p>"
                + "<p>TRUNG TÂM Y TẾ DỰ PHÒNG ĐÀ NẴNG</p>";
        helper.setText(mailContent, true);
        javaMailSender.send(message);
    }
}
