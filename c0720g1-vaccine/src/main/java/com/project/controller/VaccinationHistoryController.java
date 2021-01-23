package com.project.controller;

import com.project.entity.VaccinationHistory;
import com.project.service.VaccinationHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class VaccinationHistoryController {

    @Autowired
    private VaccinationHistoryService vaccinationHistoryService;

    //create by tu
    @GetMapping("/vaccination-history/{patientId}")
    public ResponseEntity<List<VaccinationHistory>> findAllVaccinationHistory(@PathVariable Integer patientId, Integer page) {
        page = 3;
        List<VaccinationHistory> immunizationHistoryList = vaccinationHistoryService.getAllVaccinationHistory(patientId, page);
        if (immunizationHistoryList == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(immunizationHistoryList);
    }
}
