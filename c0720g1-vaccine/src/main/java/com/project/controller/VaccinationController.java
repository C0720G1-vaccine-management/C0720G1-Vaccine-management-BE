package com.project.controller;

import com.project.dto.PeriodicVaccinationDto;
import com.project.dto.PeriodicalVaccinationRegisterDTO;
import com.project.dto.RegistrablePeriodicalVaccinationDTO;
import com.project.service.VaccinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/vaccination")
public class VaccinationController {
    @Autowired
    private VaccinationService vaccinationService;

    // luyen code
    @RequestMapping(value = "/periodic-vaccination", method = RequestMethod.GET)
    public ResponseEntity<List<PeriodicVaccinationDto>> createVaccinations() {
        List<PeriodicVaccinationDto> list = vaccinationService.findAllPeriodicVaccinations();
        if (list.isEmpty()) {
            return new ResponseEntity<List<PeriodicVaccinationDto>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<PeriodicVaccinationDto>>(list, HttpStatus.OK);
    }

    // luyen code
    @RequestMapping(value = "/periodic-vaccination/search",method = RequestMethod.GET)
    public ResponseEntity<List<PeriodicVaccinationDto>> searchVaccination(@PathVariable String name,Boolean status){
        List<PeriodicVaccinationDto> list = vaccinationService.searchPeriodicVaccinations(name,status);
        if (list.isEmpty()) {
            return new ResponseEntity<List<PeriodicVaccinationDto>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<PeriodicVaccinationDto>>(list,HttpStatus.OK);
    }

    /*KhoaTA
     *display list of registrable periodical vaccinations
     */
    @GetMapping("/register-list")
    public ResponseEntity<List<RegistrablePeriodicalVaccinationDTO>> findAllRegistrablePeriodicalVaccination() {
        List<RegistrablePeriodicalVaccinationDTO> registrableVaccinationList = this.vaccinationService.findAllRegistrableVaccination();

        if (registrableVaccinationList.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(registrableVaccinationList, HttpStatus.OK);
    }
    /*KhoaTA
     *display list of registrable periodical vaccinations
     */
    @GetMapping("/register-list/{id}")
    public ResponseEntity<RegistrablePeriodicalVaccinationDTO> findAllRegistrablePeriodicalVaccination(@PathVariable Integer id) {
        RegistrablePeriodicalVaccinationDTO registrableVaccination = this.vaccinationService.findRegistrableVaccinationById(id);
        if (registrableVaccination == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(registrableVaccination, HttpStatus.OK);
    }

    @PostMapping("/register/save")
    public ResponseEntity savePeriodicalVaccinationRegister(@Valid @RequestBody PeriodicalVaccinationRegisterDTO register ) {
        vaccinationService.save(register);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
}
