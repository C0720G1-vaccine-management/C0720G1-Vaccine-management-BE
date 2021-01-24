package com.project.controller;

import com.project.service.impl.VaccinationTransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class VaccinationTransactionController {

    @Autowired
    VaccinationTransactionServiceImpl vaccinationTransactionServiceImpl;

    @GetMapping({"/list-vaccine-transaction/name={keyword}type={keyword2}", "/list-vaccine-transaction"})
    public ResponseEntity<?> listVaccineTransaction(@PageableDefault(size = 5) Pageable pageable,
                                                    @PathVariable Optional<String> keyword,
                                                    @PathVariable Optional<String> keyword2) {
        String keyWordForSearchNamePatient = "";
        String keyWordForSearchVaccineType = "";

        if (keyword.isPresent() || keyword2.isPresent()) {
            keyWordForSearchNamePatient = keyword.get();
            keyWordForSearchVaccineType = keyword2.get();

            return new ResponseEntity<>(vaccinationTransactionServiceImpl.search(
                    keyWordForSearchNamePatient, keyWordForSearchVaccineType, pageable), HttpStatus.OK);

        }
        return new ResponseEntity<>(vaccinationTransactionServiceImpl.findAll(pageable), HttpStatus.OK);
    }

}
