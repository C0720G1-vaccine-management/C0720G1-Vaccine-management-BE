package com.project.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
/** TrungTQ code: Để cập nhật và tạo mới vaccination*/
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    private Integer times;
    private Integer duration;
    private Boolean deleteFlag;
    private Integer vaccineId;
    private Integer vaccinationTypeId;
    private Integer locationId;

}
