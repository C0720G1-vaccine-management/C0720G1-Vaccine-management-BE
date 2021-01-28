package com.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
/** TrungTQ code: Để cập nhật và tạo mới vaccination*/

@NoArgsConstructor
@AllArgsConstructor
public class VaccinationManagerDto {
    private Integer vaccinationId;
    @NotBlank(message = "Trường này không được để trống")
    private String startTime;
    @NotBlank(message = "Trường này không được để trống")
    private String endTime;
    @NotBlank(message = "Trường này không được để trống")
    private String date;
    private Boolean status;
    @NotBlank(message = "Trường này không được để trống")
    private String description;
    private Boolean deleteFlag;
    private Integer vaccineId;
    private Integer vaccinationTypeId;
    private Integer locationId;
    private Integer times;
    private Integer duration;

    public Integer getVaccinationId() {
        return vaccinationId;
    }

    public void setVaccinationId(Integer vaccinationId) {
        this.vaccinationId = vaccinationId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Integer getVaccineId() {
        return vaccineId;
    }

    public void setVaccineId(Integer vaccineId) {
        this.vaccineId = vaccineId;
    }

    public Integer getVaccinationTypeId() {
        return vaccinationTypeId;
    }

    public void setVaccinationTypeId(Integer vaccinationTypeId) {
        this.vaccinationTypeId = vaccinationTypeId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
