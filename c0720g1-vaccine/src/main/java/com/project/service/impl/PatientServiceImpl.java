package com.project.service.impl;
import com.project.dto.PatientDTO;
import com.project.entity.Patient;
import com.project.repository.PatientRepository;
import com.project.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;

    //--------CRUD-----
    @Override
    public List<Patient> findAllPatient() {
        return this.patientRepository.findAllPatient();
    }

    @Override
    public Patient findPatientById(int id) {
        return this.patientRepository.findById(id);
    }

    @Override
    public void editPatient(Patient patient) {
    }

    @Override
    public void editPatient(PatientDTO patientDTO) {
        patientRepository.editPatient(patientDTO.getName(),patientDTO.getDateOfBirth(),patientDTO.getGender(),patientDTO.getGuardian()
                ,patientDTO.getPhone(),patientDTO.getAddress(),patientDTO.getEmail());
    }


    @Override
    public void deletePatient(Patient patient) {}


    @Override
    public void addPatient(PatientDTO patientDTO) {
        patientRepository.savePatient(patientDTO.getName(),patientDTO.getDateOfBirth(),patientDTO.getGender(),patientDTO.getGuardian(),patientDTO.getPhone(),patientDTO.getAddress(),patientDTO.getEmail());
    }


    /**
     * Phuoc: tạo mới bênh nhân
     **/
    @Override
    public Patient create(Patient patientTemp) {
        return patientRepository.save(patientTemp);
    }


    /**
     * Phuoc: Tìm kiếm theo Email
     **/
    @Override
    public Integer findByEmail(String email) {
        return patientRepository.findByEmail(email);
    }

    @Override
    public Integer findByPhone(String phone) {
        return patientRepository.findByPhone(phone);
    }
}
