package com.project.service;
import com.project.dto.PatientDTO;
import com.project.entity.Patient;
import java.util.List;

public interface PatientService {

    //-----------------------------CRUD--------------
    List<Patient> findAllPatient();
    Patient findPatientById(int id);
    void editPatient(Patient patient);
    void editPatient(PatientDTO patientDTO);
    void deletePatient(Patient patient);
    void addPatient(PatientDTO patientDTO);


    /**
     * Phuoc: tạo mới bênh nhân
     **/
    Patient create(Patient patientTemp);


    /**
     * Phuoc: Tìm kiếm theo Email
     **/
    Integer findByEmail(String email);
}
