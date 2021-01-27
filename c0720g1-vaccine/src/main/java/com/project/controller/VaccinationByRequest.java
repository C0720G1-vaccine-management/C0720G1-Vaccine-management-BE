package com.project.controller;

import com.project.dto.VaccinationByRequestDTO;
import com.project.dto.VaccineDTO;
import com.project.entity.*;
import com.project.repository.VaccinationHistoryRepository;
import com.project.service.PatientService;
import com.project.service.VaccinationHistoryService;
import com.project.service.VaccinationService;
import com.project.service.VaccineService;
import com.project.validation.VaccinationByRequestDTOValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * PhuocTC
 **/

@RestController
@CrossOrigin(value = "*", allowedHeaders = "*")
@RequestMapping(value = "/api")
public class VaccinationByRequest {

    @Autowired
    private VaccineService vaccineService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private VaccinationService vaccinationService;

    @Autowired
    private VaccinationHistoryService vaccinationHistoryService;

    @Autowired
    private VaccinationByRequestDTOValidator vaccinationByRequestDTOValidator;


    /**
     * PhuocTC: Tìm kiếm + Phân trang
     **/
    @GetMapping(value = "/public/vaccine/list")
    public ResponseEntity<Page<Vaccine>> getListVaccine(@PageableDefault(size = 5) Pageable pageable,
                                                        @RequestParam(defaultValue = "") String name,
                                                        @RequestParam(defaultValue = "") String vaccineTypeName,
                                                        @RequestParam(defaultValue = "") String origin,
                                                        @RequestParam(defaultValue = "") String status) {
        Page<Vaccine> vaccineList;

        if (status.equals("false")) {
            vaccineList = vaccineService.findAllByQuantityIsNotExits(name, vaccineTypeName, origin, pageable);
        } else if (status.equals("true")) {
            vaccineList = vaccineService.findAllByQuantityIsExits(name, vaccineTypeName, origin, pageable);
        } else {
            vaccineList = vaccineService.findAllByNameContainingAndVaccineType_NameContainingAndOriginContaining(name, vaccineTypeName, origin, pageable);
        }

        if (vaccineList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(vaccineList, HttpStatus.OK);
        }


    }


    /**
     * PhuocTC: Get Vắc-xin theo Id
     **/
    @GetMapping(value = "/public/vaccination/get-vaccine/{id}")
    public ResponseEntity<VaccineDTO> registerVaccination(@PathVariable Integer id) {
        VaccineDTO vaccineDTO = vaccineService.getVaccineById(id);

        if (vaccineDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(vaccineDTO, HttpStatus.OK);
        }
    }


    /**
     * PhuocTC: Tạo mới đăng ký tim theo yêu cầu
     **/
    @PostMapping(value = "/public/vaccination/create")
    public ResponseEntity<?> registerPatient(@Valid @RequestBody VaccinationByRequestDTO vaccinationByRequestDTO,
                                             BindingResult bindingResult) {

        vaccinationByRequestDTOValidator.validate(vaccinationByRequestDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.OK);
        }


        Patient patientTemp = new Patient();
        patientTemp.setName(vaccinationByRequestDTO.getName());
        patientTemp.setDateOfBirth(vaccinationByRequestDTO.getDateOfBirth());
        patientTemp.setGender(vaccinationByRequestDTO.getGender());
        patientTemp.setGuardian(vaccinationByRequestDTO.getGuardian());
        patientTemp.setPhone(vaccinationByRequestDTO.getPhone());
        patientTemp.setAddress(vaccinationByRequestDTO.getAddress());
        patientTemp.setEmail(vaccinationByRequestDTO.getEmail());
        patientTemp.setDeleteFlag(false);

        patientTemp = patientService.create(patientTemp);

        Vaccination vaccinationTemp = new Vaccination();
        vaccinationTemp.setVaccine(vaccineService.findById(vaccinationByRequestDTO.getVaccineId()));
        vaccinationTemp.setDate(vaccinationByRequestDTO.getDateVaccination());
        vaccinationTemp.setLocation(new Location(1));
        vaccinationTemp.setVaccinationType(new VaccinationType(2));
        vaccinationTemp.setStatus(false);
        vaccinationTemp.setDeleteFlag(false);

        vaccinationTemp = vaccinationService.registerVaccination(vaccinationTemp);


        VaccinationHistory vaccinationHistoryTemp = new VaccinationHistory();
        vaccinationHistoryTemp.setPatient(patientTemp);
        vaccinationHistoryTemp.setVaccination(vaccinationTemp);
        vaccinationHistoryTemp.setStatus(false);

        vaccinationHistoryService.registerVaccinationHistory(vaccinationHistoryTemp);


        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping("/public/profile")
    public ResponseEntity<?> getListPatientByEmail(@RequestParam String email) {
        List<Patient> patientList = patientService.findAllByEmail(email, false);

        if (patientList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(patientList, HttpStatus.OK);
    }


    @GetMapping("/public/profile-personal")
    public ResponseEntity<?> getListVaccinationHistoryByPatient(@PageableDefault(size = 5) Pageable pageable,
                                                                @RequestParam(defaultValue = "") Integer patientId,
                                                                @RequestParam(defaultValue = "") String name,
                                                                @RequestParam(defaultValue = "") String date) {


        Page<VaccinationHistory> vaccinationHistoryList;

        if (date.equals("")) {
            vaccinationHistoryList =  vaccinationHistoryService.findAllByPatient_PatientIdAndVaccination_Vaccine_NameContainingAndDeleteFlag(patientId,name,false, pageable);
        } else {
            vaccinationHistoryList =  vaccinationHistoryService.findAllByPatient_PatientIdAndVaccination_Vaccine_NameContainingAndVaccination_Date(patientId,name,date,false, pageable);
        }


        if (vaccinationHistoryList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(vaccinationHistoryList, HttpStatus.OK);
    }
}
