package com.project.repository;

import com.project.entity.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
//Trung
@Transactional
public interface VaccinationManagerRepository extends JpaRepository<Vaccination, Integer> {

    @Query(
            value = "select e.* " +
                    "from Vaccination e where e.delete_flag = ?1 and e.vaccination_type_id = ?2",
            nativeQuery = true)
    List<Vaccination> findAlLVaccinationManager(int delete, int type);

    @Query(
            value = "select e.* from Vaccination e where e.vaccination_id = ?1 and e.delete_flag = ?2 and e.vaccination_type_id = ?3",
            nativeQuery = true)
    Vaccination findByIdVaccination(int vaccinationId, int delete, int type);

    @Modifying
    @Query(
            value = "insert into vaccination(`date`, delete_flag, description, start_time, end_time, status, location_id, vaccination_type_id, vaccine_id) value (?1, ?2, ?3,?4, ?5, ?6, ?7, ?8, ?9)",
            nativeQuery = true)
    void saveVaccinationManager(String date, boolean delete, String description, String startTime, String endTime, boolean status, int location, boolean type, int vaccine);
}
