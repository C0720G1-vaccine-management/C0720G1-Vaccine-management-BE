package com.project.controller;

import com.project.entity.VaccinationTransaction;
import com.project.service.VaccinationTransactionService;
import com.project.service.impl.VaccinationTransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/public")
@CrossOrigin(origins = "http://localhost:4200")
public class VaccinationTransactionController {

    @Autowired
    VaccinationTransactionService vaccinationTransactionService;

    /**
     * Made by Khanh phân trang, tìm kiếm , hiển thị list giao dịch
     */
    @GetMapping({"/vaccine-transaction-list/name={keyword}type={keyword2}", "/vaccine-transaction-list"})
    public ResponseEntity<?> listVaccineTransaction(@PageableDefault(size = 5) Pageable pageable,
                                                    @PathVariable Optional<String> keyword,
                                                    @PathVariable Optional<String> keyword2) {
        String keyWordForSearchNamePatient = "";
        String keyWordForSearchVaccineType = "";

        if (keyword.isPresent() || keyword2.isPresent()) {
            keyWordForSearchNamePatient = keyword.get();
            keyWordForSearchVaccineType = keyword2.get();

            return new ResponseEntity<>(vaccinationTransactionService.search(
                    keyWordForSearchNamePatient, keyWordForSearchVaccineType, pageable), HttpStatus.OK);

        }
        return new ResponseEntity<>(vaccinationTransactionService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping("/vaccine-transaction-create")
    public ResponseEntity<?> createVaccineTransaction(@RequestParam Integer idVaccineHistory,
                                                      @RequestParam double price,
                                                      @RequestParam Integer quantity) {
        this.vaccinationTransactionService.save(idVaccineHistory, price, quantity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/vaccine-transaction-edit/")
    public ResponseEntity<?> editVaccineTransaction(@RequestBody VaccinationTransaction vaccinationTransaction) {
        this.vaccinationTransactionService.edit(vaccinationTransaction);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/get-vaccine-transaction-id/{id}")
    public ResponseEntity<?> getId(@PathVariable Integer id) {
        return new ResponseEntity<>(this.vaccinationTransactionService.findById(id), HttpStatus.OK);
    }


}
