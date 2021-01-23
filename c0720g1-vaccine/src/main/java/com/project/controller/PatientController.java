package com.project.controller;
import com.project.dto.PatientDTO;
import com.project.entity.Patient;
import com.project.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")

public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping("/list")
    public ResponseEntity<List<Patient>> getListPatient() {
        List<Patient> listPatient = this.patientService.findAllPatient();
        return new ResponseEntity<>(listPatient, HttpStatus.OK);
    }

    @PutMapping("patient/edit/{id}")
    public ResponseEntity<Void> editPatient(@RequestBody PatientDTO patientDTO, @PathVariable int id) {
        Patient patient1 = patientService.findPatientById(id);
        if (patient1 == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            patient1.setName(patientDTO.getName());
            patient1.setDateOfBirth(patientDTO.getDateOfBirth());
            patient1.setGender(patientDTO.getGender());
            patient1.setGuardian(patientDTO.getGuardian());
            patient1.setPhone(patientDTO.getGuardian());
            patient1.setPhone(patientDTO.getPhone());
            patient1.setAddress(patientDTO.getAddress());
            patient1.setEmail(patientDTO.getEmail());
            patientService.editPatient(patient1);
            return  new ResponseEntity<>(HttpStatus.OK);
        }

    }

    @PostMapping("/patient/add")
    public ResponseEntity<Void> createPatient(@RequestBody PatientDTO patientDTO) {
        patientService.addPatient(patientDTO);
//        System.out.println(patientDTO.getEmail());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
