package com.project.repository;
import java.util.List;
import com.project.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface VaccineRepository extends JpaRepository<Vaccine,Integer> {
  
    @Query(value = "select * from vaccine_management.vaccine", nativeQuery = true)
    List<Vaccine> getAllVaccine();
  
    //Phuc create
    @Query(value = "select * from vaccine where vaccine_id = ?1", nativeQuery = true)
    Vaccine findByVaccineId(Integer id);

}

