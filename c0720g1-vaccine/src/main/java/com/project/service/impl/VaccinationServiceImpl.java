package com.project.service.impl;

import com.project.dto.*;
import com.project.entity.Vaccination;
import com.project.repository.PatientRepository;
import com.project.repository.VaccinationHistoryRepository;
import com.project.repository.VaccinationRepository;
import com.project.service.VaccinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VaccinationServiceImpl implements VaccinationService {
    @Autowired
    private VaccinationRepository vaccinationRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private VaccinationHistoryRepository vaccinationHistoryRepository;


    /*KhoaTA
     *find all periodical vaccination with date > date.now()
     */
//    @Override
//    public List<RegistrablePeriodicalVaccinationDTO> findAllRegistrableVaccination() {
//        return this.vaccinationRepository.findAllRegistrableVaccination();
//    }
    /*KhoaTA
     *find a periodical vaccination by Id
     */
    @Override
    public RegistrablePeriodicalVaccinationDTO findRegistrableVaccinationById(Integer id) {
        return this.vaccinationRepository.findRegistrableVaccinationById(id);
    }
    /*KhoaTA
     *save patient and register for periodical vaccination
     */
    @Override
    public void saveRegister(PeriodicalVaccinationRegisterDTO register) {
        this.patientRepository.savePatient(register.getName(), register.getDateOfBirth(), register.getGender(), register.getGuardian(), register.getPhone(), register.getAddress(), register.getEmail());
        int patientId = this.patientRepository.findLatestPatientId();
        this.vaccinationHistoryRepository.savePeriodicalVaccinationRegister(register.getVaccinationId(), patientId);
    }


    /**
     * Phuoc: Tạo mới lịch tiêm theo yêu cầu
     **/
    @Override
    public Vaccination registerVaccination(Vaccination vaccinationTemp) {
        return vaccinationRepository.save(vaccinationTemp);
    }

    /*KhoaTA
     *get the list of all vaccine's ages
     */
    @Override
    public List<String> findAllVaccineAge() {
        return this.vaccinationRepository.findAllAge();
    }

    /*KhoaTA
     *find all available vaccination time stamps
     */
    @Override
    public List<TimeDTO> findAllVaccinationTime() {
        return this.vaccinationRepository.findAllTimeStamp();
    }

    /*KhoaTA
     *get the total page of search data
     */
    @Override
    public double getTotalPage(PeriodicalSearchDataDTO searchData) {
        if (searchData.getDate().equals("")) {
            return Math.ceil((double) this.vaccinationRepository.findTotalPage('%'+searchData.getAge()+'%', '%'+searchData.getStartTime()+'%', '%'+searchData.getEndTime()+'%',
                    '%'+searchData.getVaccineName()+'%').size()/5);
        }
        return Math.ceil( (double) this.vaccinationRepository.findTotalPage('%'+searchData.getAge()+'%', searchData.getDate(), '%'+searchData.getStartTime()+'%', '%'+searchData.getEndTime()+'%',
                '%'+searchData.getVaccineName()+'%').size()/5);
    }

    /*KhoaTA
     *get list of periodical vaccination with custom search and page
     */
    @Override
    public List<RegistrablePeriodicalVaccinationDTO> findCustomVaccination(PeriodicalSearchDataDTO searchData) {
        if (searchData.getDate().equals("")) {
            return this.vaccinationRepository.findCustomListWithPageWithoutDate('%'+searchData.getAge()+'%', '%'+searchData.getStartTime()+'%', '%'+searchData.getEndTime()+'%',
                    '%'+searchData.getVaccineName()+'%', (searchData.getCurrentPage()-1)*5);
        } else {
            return this.vaccinationRepository.findCustomListWithPageWithDate('%'+searchData.getAge()+'%', '%'+searchData.getDate()+'%', '%'+searchData.getStartTime()+'%', '%'+searchData.getEndTime()+'%',
                    '%'+searchData.getVaccineName()+'%', (searchData.getCurrentPage()-1)*5);
        }
    }
}
