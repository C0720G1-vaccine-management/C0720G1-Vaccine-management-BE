package com.project.service;
import java.util.List;
import com.project.entity.Vaccine;

public interface VaccineService {
    List<Vaccine> getAllVaccine();
        //Phuc create
    Vaccine findById(Integer id);

}
