package com.project.controller;

import com.project.entity.Vaccine;
import com.project.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api")
public class VaccineController {

    @Autowired
    private VaccineService vaccineService;

    @GetMapping("/vaccines")
    public ResponseEntity<List<Vaccine>> getAllVaccine() {
        List<Vaccine> vaccines = vaccineService.getAllVaccine();
        if (vaccines.isEmpty()) {
            return new ResponseEntity<List<Vaccine>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Vaccine>>(vaccines, HttpStatus.OK);
    }
}
