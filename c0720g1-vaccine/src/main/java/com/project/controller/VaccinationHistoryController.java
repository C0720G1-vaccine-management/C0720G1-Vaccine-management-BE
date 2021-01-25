package com.project.controller;


import com.project.dto.VaccinationHistoryDTO;
import com.project.dto.VaccinationHistoryFeedbackDTO;
import com.project.dto.VaccinationHistorySendFeedbackDTO;
import com.project.entity.VaccinationHistory;
import com.project.service.VaccinationHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/public")
public class VaccinationHistoryController {

    @Autowired
    private VaccinationHistoryService vaccinationHistoryService;

    /**
     * LuyenNT code
     *
     * @return
     */
    @RequestMapping(value = "/periodic-vaccination/list", method = RequestMethod.GET)
    public ResponseEntity<Page<VaccinationHistory>> findAllPeriodicVaccination(@PageableDefault(size = 2) Pageable pageable,
                                                                               @RequestParam(defaultValue = "") String name) {
        Page<VaccinationHistory> list = vaccinationHistoryService.finAllPeriodicVaccination(name, pageable);
        if (list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/periodic-vaccination/search", method = RequestMethod.GET)
    public ResponseEntity<Page<VaccinationHistory>> searchPeriodicVaccination(@PageableDefault(size = 2) Pageable pageable,
                                                                              @RequestParam(defaultValue = "") String name,
                                                                              @RequestParam(defaultValue = "") String status) {
        Page<VaccinationHistory> list = null;
        Boolean statusNew = false;
        if (status.equals("")) {
            list = vaccinationHistoryService.finAllPeriodicVaccination(name, pageable);
        } else if (status.equals("true")) {
            statusNew = true;
            list = vaccinationHistoryService.searchPeriodicVaccination(name, statusNew, pageable);
        } else if (status.equals("false")) {
            statusNew = false;
            list = vaccinationHistoryService.searchPeriodicVaccination(name, statusNew, pageable);
        }
        if (list.isEmpty()) {
            return new ResponseEntity<>(list, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    /* tuNH */
    @RequestMapping(value = "/vaccination-history/{patientId}", method = RequestMethod.GET)
    public ResponseEntity<List<VaccinationHistoryDTO>> findAllVaccinationHistory(@PathVariable Integer patientId) {
        List<VaccinationHistoryDTO> list = this.vaccinationHistoryService.getAllVaccinationHistory(patientId);
        if (list.isEmpty()) {
            return new ResponseEntity<List<VaccinationHistoryDTO>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<VaccinationHistoryDTO>>(list, HttpStatus.OK);
    }

    /* tuNH */
    @RequestMapping(value = "/vaccination-history/feedback/{vaccinationHistoryId}", method = RequestMethod.GET)
    public ResponseEntity<VaccinationHistoryFeedbackDTO> findByIdVaccinationHistory(@PathVariable Integer vaccinationHistoryId) {
        VaccinationHistoryFeedbackDTO list = this.vaccinationHistoryService.findByIdVaccinationHistory(vaccinationHistoryId);
        if (list == null) {
            return new ResponseEntity<VaccinationHistoryFeedbackDTO>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<VaccinationHistoryFeedbackDTO>(list, HttpStatus.OK);
    }

    /* tuNH */
    @RequestMapping(value = "/vaccination-history/feedback/sendFeedback/{vaccinationHistoryId}", method = RequestMethod.POST)
    public ResponseEntity<Void> feedbackVaccinationHistory(
            @RequestBody VaccinationHistorySendFeedbackDTO vaccinationHistorySendFeedbackDTO,
            @PathVariable Integer vaccinationHistoryId) {

        this.vaccinationHistoryService.updateVaccinationHistory(vaccinationHistoryId, vaccinationHistorySendFeedbackDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
