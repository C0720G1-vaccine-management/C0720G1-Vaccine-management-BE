package com.project.controller;

import com.project.dto.VaccinationHistoryFeedbackDTO;
import com.project.dto.VaccinationHistoryGetAfterStatusDTO;
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

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/public")
public class VaccinationHistoryController {

    @Autowired
    private VaccinationHistoryService vaccinationHistoryService;

    /**
     * tuNH
     * lấy danh lịch sử tiêm chủng, phân trang , tìm kiếm
     **/
    @RequestMapping(value = "/vaccination-history", method = RequestMethod.GET)
    public ResponseEntity<Page<VaccinationHistory>> findAllVaccinationHistory(@PageableDefault(size = 5) Pageable pageable,
                                                                              @RequestParam(defaultValue = "") String vaccineName,
                                                                              @RequestParam(defaultValue = "") String vaccinationDate,
                                                                              @RequestParam(defaultValue = "") Integer patientId) {
        Page<VaccinationHistory> vaccinationHistories;
        if (vaccineName.isEmpty() && vaccinationDate.isEmpty()) {
            vaccinationHistories = this.vaccinationHistoryService.getAllVaccinationHistory(vaccineName, vaccinationDate, patientId, pageable);

        }
        vaccinationHistories = this.vaccinationHistoryService.getAllVaccinationHistory(vaccineName, vaccinationDate, patientId, pageable);
        if (vaccinationHistories == null) {
            return new ResponseEntity<Page<VaccinationHistory>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Page<VaccinationHistory>>(vaccinationHistories, HttpStatus.OK);
    }

    /**
     * tuNH
     * findById lịch sử tiêm chủng
     **/
    @RequestMapping(value = "/vaccination-history/feedback/{vaccinationHistoryId}", method = RequestMethod.GET)
    public ResponseEntity<VaccinationHistoryFeedbackDTO> findByIdVaccinationHistory(@PathVariable Integer vaccinationHistoryId) {
        VaccinationHistoryFeedbackDTO list = this.vaccinationHistoryService.findByIdVaccinationHistory(vaccinationHistoryId);
        if (list == null) {
            return new ResponseEntity<VaccinationHistoryFeedbackDTO>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<VaccinationHistoryFeedbackDTO>(list, HttpStatus.OK);
    }

    /**
     * tuNH
     * lấy trạng thái sau khi tiêm chủng lịch sử tiêm chủng
     **/
    @RequestMapping(value = "/vaccination-history/feedback/getAfterStatus/{vaccinationHistoryId}", method = RequestMethod.GET)
    public ResponseEntity<VaccinationHistoryGetAfterStatusDTO> getAfterStatusVaccinationHistory(@PathVariable Integer vaccinationHistoryId) {
        VaccinationHistoryGetAfterStatusDTO afterStatus = this.vaccinationHistoryService.getAfterStatusVaccinationHistory(vaccinationHistoryId);
        if (afterStatus == null) {
            return new ResponseEntity<VaccinationHistoryGetAfterStatusDTO>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<VaccinationHistoryGetAfterStatusDTO>(afterStatus, HttpStatus.OK);
    }

    /**
     * tuNH
     * phản hồi sau khi tiêm chủng
     **/
    @RequestMapping(value = "/vaccination-history/feedback/sendFeedback/{vaccinationHistoryId}", method = RequestMethod.PUT)
    public ResponseEntity<Void> feedbackVaccinationHistory(
            @RequestBody VaccinationHistorySendFeedbackDTO vaccinationHistorySendFeedbackDTO,
            @PathVariable Integer vaccinationHistoryId
    ) {
        this.vaccinationHistoryService.updateVaccinationHistory(vaccinationHistoryId, vaccinationHistorySendFeedbackDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
