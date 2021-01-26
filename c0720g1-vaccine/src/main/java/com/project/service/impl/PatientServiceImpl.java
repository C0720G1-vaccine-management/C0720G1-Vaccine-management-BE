package com.project.service.impl;
import com.project.dto.PatientDTO;
import com.project.entity.Patient;
import com.project.repository.PatientRepository;
import com.project.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;

    /**
     * Duy NP
     **/
    @Override
    public Page<Patient> findAllPatient2(Pageable pageable) {
        return patientRepository.findAllByDeleteFlagIsFalse(pageable);
    }
    /**
     * Duy NP
     **/
    @Override
    public Patient findPatientById(int id) {
        return this.patientRepository.findById(id);
    }
    /**
     * Duy NP
     **/
    @Override
    public Page<Patient> search(String name, String id, int pageable) {
        if (id.length() > 3) {
            id = id.substring(3);
        }
        List<Patient> patientList = patientRepository.search(name,id);
        Pageable pageable1 = PageRequest.of(pageable , 3);
        int start = (int) pageable1.getOffset();
        int end = Math.min((start + pageable1.getPageSize()), patientList.size());
        Page<Patient> pages = new PageImpl<Patient>(patientList.subList(start, end), pageable1, patientList.size());
        return pages;
    }
    /**
     * Duy NP
     **/
    @Override
    public void editPatient(Patient patient) {
        this.patientRepository.editPatient(patient.getName(),patient.getDateOfBirth(),patient.getGender(),patient.getGuardian(),patient.getPhone(),patient.getAddress(),patient.getEmail(), patient.getPatientId());
    }


    /**
     *NhiTTY
     **/
    @Override
    public void addPatient(PatientDTO patientDTO) {
        patientRepository.addPatient(patientDTO.getName(), patientDTO.getDateOfBirth(), patientDTO.getGender(), patientDTO.getGuardian(), patientDTO.getPhone(), patientDTO.getAddress(), patientDTO.getEmail());
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
