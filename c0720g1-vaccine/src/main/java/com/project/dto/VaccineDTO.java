package com.project.dto;

public interface VaccineDTO {

    Integer getVaccineId();
    String getName();
    String getLicenseCode();
    String getOrigin();
    String getAge();
    String getMaintenance();
    Double getDosage();
    String getExpired();
    Long getQuantity();
    Boolean getDeleteFlag();
    String getVaccineTypeName();

}
