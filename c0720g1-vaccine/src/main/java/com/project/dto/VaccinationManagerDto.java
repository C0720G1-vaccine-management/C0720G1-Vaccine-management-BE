package com.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
/** TrungTQ code: Để cập nhật và tạo mới vaccination*/
@Data
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

}
