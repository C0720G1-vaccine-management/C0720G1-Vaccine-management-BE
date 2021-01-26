package com.project.controller;

import com.project.dto.VaccinationHistoryFeedbackDTO;
import com.project.dto.VaccinationHistoryGetAfterStatusDTO;
import com.project.dto.VaccinationHistoryRegisteredDTO;
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
     /** LuyenNT code
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

  
    /**
    * Khanh
    */
    @RequestMapping(value = "/vaccination-history-list", method = RequestMethod.GET)
    public ResponseEntity<?> getListVaccinationHistory (){
        List<VaccinationHistory> list = this.vaccinationHistoryService.findAll();
        if (list==null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }


    /**
     list:  create by LongBP
     **/
    @RequestMapping(value = "/public/registered-for-vaccination/list", method = RequestMethod.GET)
    public ResponseEntity<Page<VaccinationHistory>> getAllRegisteredVaccination(@PageableDefault(size = 3) Pageable pageable,
                                                                                @RequestParam(defaultValue = "") String name) {
        Page<VaccinationHistory> list = vaccinationHistoryService.getAllRegisteredRequired(name, pageable);
        if (list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     search and paging:  create by LongBP
     **/
    @RequestMapping(value = "/public/registered-for-vaccination/search", method = RequestMethod.GET)
    public ResponseEntity<Page<VaccinationHistory>> searchRegisteredVaccination(@PageableDefault(size = 3) Pageable pageable,
                                                                                @RequestParam(defaultValue = "") String name,
                                                                                @RequestParam(defaultValue = "") String status) {
        Page<VaccinationHistory> list = null;
        Boolean statusNew;
        if (status.equals("")) {
            list = vaccinationHistoryService.getAllRegisteredRequired(name, pageable);
        } else if (status.equals("true")) {
            statusNew = true;
            list = vaccinationHistoryService.searchNameAndInjected(name, statusNew, pageable);
        } else if (status.equals("false")) {
            statusNew = false;
            list = vaccinationHistoryService.searchNameAndInjected(name, statusNew, pageable);
        }
        if (list.isEmpty()) {
            return new ResponseEntity<>(list, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     find by id:  create by LongBP
     **/
    @GetMapping("/public/registered-for-vaccination/view/{id}")
    public ResponseEntity<VaccinationHistoryRegisteredDTO> viewEmployee(@PathVariable Integer id) {
        VaccinationHistoryRegisteredDTO vaccinationHistoryRegisteredDTO = vaccinationHistoryService.findId(id);
        if (vaccinationHistoryRegisteredDTO == null) {
            return new ResponseEntity<VaccinationHistoryRegisteredDTO>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<VaccinationHistoryRegisteredDTO>(vaccinationHistoryRegisteredDTO, HttpStatus.OK);
    }



}
