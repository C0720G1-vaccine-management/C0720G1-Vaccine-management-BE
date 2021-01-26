package com.project.repository;

import com.project.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    @Query(value = "select *from patient ;", nativeQuery = true)
    List<Patient> findAllPatient();


    @Query(value = "select * from patient where patient_id = ?", nativeQuery = true)
    Patient findById(int id);

    @Query(value = "update patient as p set p.name =?1,p.date_of_birth =?2,p.gender =?3" +
            ",p.guardian =?4,p.phone =?5,p.address =?6,p.email =?7 where patient_id = ?8", nativeQuery = true)
    void editPatient(String name, String date_of_birth, String gender, String guardian, String phone,
                     String address, String email);

    @Query(value = "delete from patient where patient_id = ?;", nativeQuery = true)
    void deletePatient(int id);

    /**
     * NhiTTY
     **/
    @Modifying
    @Query(value = "insert into patient(name,date_of_birth,gender,guardian,phone,address,email,delete_flag) values (?1,?2,?3,?4,?5,?6,?7,b'0')", nativeQuery = true)
    void addPatient(String name, String dateOfBirth, String gender, String guardian, String phone, String address, String email);

    /**
     * Phuoc: Tìm kiếm theo Email
     **/
    @Query(value = "select count(email) from patient where email = ?", nativeQuery = true)
    Integer findByEmail(String email);

    /**
     * Phuoc: Tìm kiếm theo Phone
     **/
    @Query(value = "select count(phone) from patient where phone = ?", nativeQuery = true)
    Integer findByPhone(String email);

    /*KhoaTA
     * Get the id of latest patient
     */
    @Query(value = "select max(patient_id) from patient", nativeQuery = true)
    int findLatestPatientId();

}
