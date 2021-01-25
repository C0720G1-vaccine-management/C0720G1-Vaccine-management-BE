package com.project.controller;

import com.project.dto.VaccinationByRequestDTO;
import com.project.dto.VaccineDTO;
import com.project.entity.*;
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

@RestController
@CrossOrigin(value = "*", allowedHeaders = "*")
@RequestMapping(value = "/api/public")
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
     * Phuoc: Tìm kiếm + Phân trang
     **/
    @GetMapping(value = "/vaccine/list")
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
        }

        return new ResponseEntity<>(vaccineList, HttpStatus.OK);
    }


    /**
     * Get Vắc-xin theo Id
     **/
    @GetMapping(value = "/vaccination/get-vaccine/{id}")
    public ResponseEntity<VaccineDTO> registerVaccination(@PathVariable Integer id) {
        VaccineDTO vaccineDTO = vaccineService.getVaccineById(id);

        if (vaccineDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(vaccineDTO, HttpStatus.OK);
    }


    /**
     * Phuoc: Tạo mới đăng ký tim theo yêu cầu
     **/
    @PostMapping(value = "/vaccination/create")
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


        return new ResponseEntity<>(vaccinationByRequestDTO, HttpStatus.OK);
    }


}
