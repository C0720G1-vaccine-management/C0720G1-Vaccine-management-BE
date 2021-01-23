package com.project.controller;

import com.project.dto.PatientDTO;
import com.project.dto.VaccinationByRequestDTO;
import com.project.dto.VaccineDTO;
import com.project.entity.Patient;
import com.project.entity.Vaccine;
import com.project.repository.VaccineRepository;
import com.project.service.PatientService;
import com.project.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "*", allowedHeaders = "*")
@RequestMapping(value = "/api/public")
public class VaccinationByRequest {

    @Autowired
    private VaccineService vaccineService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private VaccineRepository vaccineRepository;


    @GetMapping(value = "/vaccine/list")
    public ResponseEntity<Page<Vaccine>> getListVaccine(@PageableDefault(size = 5) Pageable pageable,
                                                        @RequestParam(defaultValue = "") String name,
                                                        @RequestParam(defaultValue = "") String vaccineTypeName,
                                                        @RequestParam(defaultValue = "") String origin) {
//        Page<Vaccine> vaccineList = vaccineService.findAllByNameContainingAndVaccineType_NameContainingAndOriginContaining(name, vaccineTypeName, origin, pageable);

        Page<Vaccine> vaccineList = vaccineRepository.findAll(pageable);

        if (vaccineList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(vaccineList, HttpStatus.OK);
    }


    @GetMapping(value = "/vaccination/create/{id}")
    public ResponseEntity<VaccineDTO> registerVaccination(@PathVariable Integer id) {
        VaccineDTO vaccineDTO = vaccineService.getVaccineById(id);

        if (vaccineDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(vaccineDTO, HttpStatus.OK);
    }


    @PostMapping(value = "/patient/create")
    public ResponseEntity<VaccinationByRequestDTO> registerPatient(@RequestBody VaccinationByRequestDTO vaccinationByRequestDTO) {
        Patient patientTemp = new Patient();
        patientTemp.setName(vaccinationByRequestDTO.getName());
        patientTemp.setDateOfBirth(vaccinationByRequestDTO.getDateOfBirth());
        patientTemp.setGender(vaccinationByRequestDTO.getGender());
        patientTemp.setGuardian(vaccinationByRequestDTO.getGuardian());
        patientTemp.setPhone(vaccinationByRequestDTO.getPhone());
        patientTemp.setAddress(vaccinationByRequestDTO.getAddress());
        patientTemp.setEmail(vaccinationByRequestDTO.getEmail());
        patientTemp.setDeleteFlag(false);
        patientService.create(patientTemp);


        return new ResponseEntity<>(vaccinationByRequestDTO, HttpStatus.OK);
    }


}
