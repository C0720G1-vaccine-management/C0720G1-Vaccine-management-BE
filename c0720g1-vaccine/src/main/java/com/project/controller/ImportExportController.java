package com.project.controller;

import com.project.entity.Vaccine;
import com.project.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.project.service.ImportAndExportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/public")
@CrossOrigin(origins = "http://localhost:4200")
public class ImportExportController {
    @Autowired
    private ImportAndExportService importAndExportService;


    //phuc create
    @Autowired
    private VaccineService vaccineService;
    /**
     * Made by Khanh tìm kiếm , phân trang, hiển thị list vaccine
     */
    @GetMapping({"/vaccine-price-list/search1={keyword}search2={keyword2}search3={keyword3}", "/vaccine-price-list"})
    public ResponseEntity<?> listPriceVaccine(@PageableDefault(size = 5) Pageable pageable,
                                              @PathVariable Optional<String> keyword,
                                              @PathVariable Optional<String> keyword2,
                                              @PathVariable Optional<String> keyword3) {
        String keyWordForSearchVaccineId = "";
        String keyWordForSearchVaccineType = "";
        String keyWordForSearchVaccineOrigin = "";
        if (keyword.isPresent() || keyword2.isPresent() || keyword3.isPresent()) {
            keyWordForSearchVaccineId = keyword.orElse("0");
            keyWordForSearchVaccineType = keyword2.get();
            keyWordForSearchVaccineOrigin = keyword3.get();
            return new ResponseEntity<>(importAndExportService.search("export", Integer.parseInt(keyWordForSearchVaccineId),
                    keyWordForSearchVaccineType, keyWordForSearchVaccineOrigin, pageable), HttpStatus.OK);
        }
        return new ResponseEntity<>(importAndExportService.findAll("export", pageable), HttpStatus.OK);
    }

    /**
     * Made by Khanh chỉnh sửa giá vaccine
     */
    @PutMapping("/vaccine-price-edit/{id}/{price}")
    public ResponseEntity<?> editPriceVaccine(@PathVariable Integer id,@PathVariable Long price) {
        this.importAndExportService.editPrice(id,price);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    /**
     * Made by Khanh lấy đối tượng export bằng id
     */
    @GetMapping("/getExportId/{id}")
    public ResponseEntity<?> getId(@PathVariable Integer id) {
        return new ResponseEntity<>(this.importAndExportService.findById(id), HttpStatus.OK);
    }

    //phuc create
    @GetMapping("/{id}/exportVaccine")
    public String exportVaccine(@PathVariable Integer id, Model model) {
        Vaccine vaccine = vaccineService.findById(id);
        return null;
    }
}
