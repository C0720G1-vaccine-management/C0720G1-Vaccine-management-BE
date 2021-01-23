package com.project.controller;
import com.project.entity.Vaccination;
import com.project.dto.VaccinationDto;
import com.project.service.VaccinationManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;

//Trung - controller for managing periodical vaccinations
@RestController
@RequestMapping("/api/vaccination-manager")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class VaccinationManagerController {

    //Trung
    @Autowired
    private VaccinationManagerService vaccinationManagerService;

    //Trung - list all periodical vaccinations for admin
    @RequestMapping(value = "/vaccinations", method = RequestMethod.GET)
    public ResponseEntity<List<Vaccination>> listAllVaccination() {
        List<Vaccination> vaccinations = vaccinationManagerService.findAllVaccinationManager();
        if (vaccinations.isEmpty()) {
            return new ResponseEntity<List<Vaccination>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Vaccination>>(vaccinations, HttpStatus.OK);
    }

    //Trung - get periodical vaccinations by id for admin
    @RequestMapping(value = "/vaccinations/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vaccination> getVaccinationById(@PathVariable("id") Integer id) {
        Vaccination vaccination = vaccinationManagerService.findById(id);
        if (vaccination == null) {
            return new ResponseEntity<Vaccination>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Vaccination>(vaccination, HttpStatus.OK);
    }
    //Trung - create periodical vaccinations for admin
    @RequestMapping(value = "/vaccinations", method = RequestMethod.POST)
    public ResponseEntity<Void> createVaccinations(@RequestBody VaccinationDto vaccinationDto, UriComponentsBuilder ucBuilder) {
        vaccinationManagerService.save(vaccinationDto);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/vaccinations/{id}").buildAndExpand(vaccinationDto.getVaccinationId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

        //    @RequestMapping(value = "/vaccinations/{id}", method = RequestMethod.PUT)
//    public ResponseEntity<Vaccination> updateVaccination(@PathVariable("id") Integer id, @RequestBody Vaccination vaccination) {
//
//        Vaccination currentVaccination = vaccinationService.findById(id);
//
//        if (currentVaccination == null) {
//            return new ResponseEntity<Vaccination>(HttpStatus.NOT_FOUND);
//        }
//        currentVaccination.setStartTime(vaccination.getStartTime());
//        currentVaccination.setEndTime(vaccination.getEndTime());
//        currentVaccination.setDate(vaccination.getDate());
//        currentVaccination.setStatus(vaccination.getStatus());
//        currentVaccination.setDescription(vaccination.getDescription());
//        currentVaccination.setVaccine(vaccination.getVaccine());
//        currentVaccination.setLocation(vaccination.getLocation());
//        currentVaccination.setDeleteFlag(vaccination.getDeleteFlag());
//        currentVaccination.setVaccinationId(vaccination.getVaccinationId());
//
//        vaccinationService.save(currentVaccination);
//        return new ResponseEntity<Vaccination>(currentVaccination, HttpStatus.OK);
//    }

    //Trung - delete periodical vaccinations by id for admin
    @RequestMapping(value = "/vaccinations/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Vaccination> deleteVaccination(@PathVariable("id") Integer id) {

        Vaccination vaccination = vaccinationManagerService.findById(id);
        if (vaccination == null) {
            return new ResponseEntity<Vaccination>(HttpStatus.NOT_FOUND);
        }
        vaccinationManagerService.remove(id);
        return new ResponseEntity<Vaccination>(HttpStatus.NO_CONTENT);
    }
}
