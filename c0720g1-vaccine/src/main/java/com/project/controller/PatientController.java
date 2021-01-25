package com.project.controller;

import com.project.dto.PatientDTO;
import com.project.entity.Patient;
import com.project.service.PatientService;
import com.project.validation.PatientByRequestDTOValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/public/")
@CrossOrigin(origins = "*")

public class PatientController {
    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientByRequestDTOValidator patientByRequestDTOValidator;

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
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }

    /**
     * NhiTTY
     **/
    @PostMapping(value = "/patient/create")
    public ResponseEntity<?> createPatient(@Valid @RequestBody PatientDTO patientDTO, BindingResult bindingResult) {
        patientByRequestDTOValidator.validate(patientDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.OK);
        }
        patientService.addPatient(patientDTO);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

}
