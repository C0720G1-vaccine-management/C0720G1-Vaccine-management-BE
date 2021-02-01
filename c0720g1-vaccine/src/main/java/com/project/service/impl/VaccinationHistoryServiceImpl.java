package com.project.service.impl;

import com.project.dto.*;
import com.project.entity.Patient;
import com.project.entity.VaccinationHistory;
import com.project.entity.Vaccine;
import com.project.repository.PatientRepository;
import com.project.repository.VaccinationHistoryRepository;
import com.project.repository.VaccinationRepository;
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

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private VaccinationRepository vaccinationRepository;

    /**
     * tuNH
     **/
    @Override
    public Page<VaccinationHistory> getAllVaccinationHistory(String vaccineName, String vaccinationDate, int patientId, Pageable pageable) {
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
     *Nguyen Van Linh
     */
    @Override
    public List<String> getEmailToSendOfVaccinationMore() {
        return vaccinationHistoryRepository.getEmailToSendOfVaccinationMore();
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
        return vaccinationHistoryRepository.findAllByPatient_NameContainingAndVaccination_StatusIsAndVaccination_VaccinationType_VaccinationTypeId(name, status,1, pageable);
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
        return vaccinationHistoryRepository.findAllByPatient_NameContainingAndVaccination_VaccinationType_VaccinationTypeId(name,1, pageable);
    }
    /**
     * LuyenNT
     * @param pageable
     * @return
     */
    @Override
    public Page<VaccinationHistory> finAllPeriodicVaccination(Pageable pageable) {
        return vaccinationHistoryRepository.findAllByVaccination_VaccinationType_VaccinationTypeId(1,pageable);
    }

    /**
     * list:  create by LongBP
     */
    @Override
    public Page<VaccinationHistory> getAllRegisteredRequired(String name,Integer id, Pageable pageable) {
        return this.vaccinationHistoryRepository.findAllByPatient_NameContainingAndVaccination_VaccinationType_VaccinationTypeId(name,id, pageable);
    }


    /**
     * search and paging:  create by LongBP
     **/
    @Override
    public Page<VaccinationHistory> searchNameAndInjected(String name,Integer id, Boolean status, Pageable pageable) {
        return this.vaccinationHistoryRepository.findAllByPatient_NameContainingAndVaccination_VaccinationType_VaccinationTypeIdAndStatusIs(name, id, status, pageable);
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

    @Override
    public void sendMail(VaccinationByRequestDTO vaccinationByRequestDTO, Patient patientTemp, Vaccine vaccineTemp) throws MessagingException, UnsupportedEncodingException {
        String subject = "Phiếu đăng ký tiêm của bạn";
        String mailContent = "";

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
        helper.setTo(patientTemp.getEmail());
        helper.setFrom("vanlinh12b5@gmail.com", "TRUNG TÂM Y TẾ DỰ PHÒNG ĐÀ NẴNG");
        helper.setSubject(subject);
        mailContent = "<p sytle='color:red;'>Phản hồi từ người dùng ,<p>"
                + "<p sytle='color:red;'>Nội dung:" + patientTemp.getAddress() + "<p>"
                + "<p sytle='color:red;'> Bạn có một email phản hồi từ " + patientTemp.getEmail() + "<p>"
                + "<p>TRUNG TÂM Y TẾ DỰ PHÒNG ĐÀ NẴNG</p>";
        helper.setText(mailContent, true);
        javaMailSender.send(message);

    }

    /**
     * KhoaTA
     * Save new register for a vaccination
     */
    @Override
    public VaccinationHistory createNewRegister(PeriodicalVaccinationTempRegisterDTO register) {
        VaccinationHistory vaccinationHistory = new VaccinationHistory();
        vaccinationHistory.setPatient(this.patientRepository.getOne(register.getPatientId()));
        vaccinationHistory.setVaccination(this.vaccinationRepository.getOne(register.getVaccinationId()));
        vaccinationHistory.setStartTime(register.getStartTime());
        vaccinationHistory.setEndTime(register.getEndTime());
        vaccinationHistory.setDeleteFlag(false);
        return this.vaccinationHistoryRepository.save(vaccinationHistory);
    }

    /**
     * Phuoc
     **/
    @Override
    public Page<VaccinationHistory> findAllByPatientId(Integer patientId, boolean b, Pageable pageable) {
        return vaccinationHistoryRepository.findAllByPatient_PatientIdAndDeleteFlag(patientId, b, pageable);
    }

    @Override
    public Page<VaccinationHistory> findAllByPatient_PatientIdAndVaccination_Vaccine_NameContainingAndVaccination_Date(Integer patient_patientId, String vaccination_vaccine_name, String vaccination_date,Boolean b, Pageable pageable) {
        return vaccinationHistoryRepository.findAllByPatient_PatientIdAndVaccination_Vaccine_NameContainingAndVaccination_DateAndDeleteFlag(patient_patientId, vaccination_vaccine_name, vaccination_date, b, pageable);
    }

    @Override
    public Page<VaccinationHistory> findAllByPatient_PatientIdAndVaccination_Vaccine_NameContainingAndDeleteFlag(Integer patient_patientId, String vaccination_vaccine_name, Boolean deleteFlag, Pageable pageable) {
        return vaccinationHistoryRepository.findAllByPatient_PatientIdAndVaccination_Vaccine_NameContainingAndDeleteFlag(patient_patientId, vaccination_vaccine_name, deleteFlag, pageable);
    }

    @Override
    public List<VaccinationHistory> findAllByVaccinationTransactionIsNull() {
        return vaccinationHistoryRepository.findAllByVaccinationTransactionIsNull();
    }

    @Override
    public Integer getAllVaccinationByDate(String date, boolean b) {
        return vaccinationHistoryRepository.countAllByVaccination_DateAndDeleteFlag(date, b);
    }

    @Override
    public Integer getAllVaccinationByDate(String date, String time, boolean b) {
        return vaccinationHistoryRepository.countAllByVaccination_DateAndStartTimeAndDeleteFlag(date, time, b);
    }


}
