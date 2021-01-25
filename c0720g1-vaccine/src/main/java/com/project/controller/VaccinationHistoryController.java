package com.project.controller;


import com.project.dto.VaccinationHistoryDTO;
import com.project.dto.VaccinationHistoryFeedbackDTO;
import com.project.dto.VaccinationHistorySendFeedbackDTO;
import com.project.entity.VaccinationHistory;
import com.project.service.VaccinationHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
//@EnableAutoConfiguration
@RequestMapping("/api/public")
public class VaccinationHistoryController {

    @Autowired
    private VaccinationHistoryService vaccinationHistoryService;


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
    @RequestMapping(value = "/vaccination-history-list", method = RequestMethod.GET)
    public ResponseEntity<?> getListVaccinationHistory (){
        List<VaccinationHistory> list = this.vaccinationHistoryService.findAll();
        if (list==null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
}
