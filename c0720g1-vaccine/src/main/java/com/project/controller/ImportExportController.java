package com.project.controller;

import com.project.entity.Vaccine;
import com.project.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.project.dto.ImportAndExportDTO;
import com.project.entity.Account;
import com.project.entity.ImportAndExport;
import com.project.service.AccountService;
import com.project.service.ImportAndExportService;
import com.project.service.StorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class ImportExportController {
    @Autowired
    private ImportAndExportService importAndExportService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private StorageService storageService;

    //phuc create
    @Autowired
    private VaccineService vaccineService;

    @GetMapping("/list-vaccine-price")
    public ResponseEntity<?> listPriceVaccine() {
        return new ResponseEntity<>(importAndExportService.findAll(), HttpStatus.OK);
    }

    /**
     * Made by Khanh
     */

    @PostMapping("/vaccine-price-edit/{id}")
    public ResponseEntity<?> createPriceVaccine(@RequestBody ImportAndExportDTO importAndExportDTO, @PathVariable int id) {
        ImportAndExport importAndExport = importAndExportService.findById(id);
        importAndExport.setPrice(importAndExportDTO.getPrice());
        importAndExport.setDate(importAndExportDTO.getDate());
        importAndExport.setAction(importAndExportDTO.getAction());
        importAndExport.setDeleteFlag(importAndExportDTO.getDeleteFlag());
        importAndExport.setQuantity(importAndExportDTO.getQuantity());
        this.importAndExportService.save(importAndExport);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //phuc create
    @GetMapping("/{id}/exportVaccine")
    public String exportVaccine(@PathVariable Integer id, Model model) {

        Vaccine vaccine = vaccineService.findById(id);
        return null;
    }
}
