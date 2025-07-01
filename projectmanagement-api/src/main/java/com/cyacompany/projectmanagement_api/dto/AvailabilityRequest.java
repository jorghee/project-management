package com.cyacompany.projectmanagement_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AvailabilityRequest {

  @NotBlank
  @Size(max = 1)
  private String availabilityStatus;
  
  @NotNull
  @PositiveOrZero
  private Integer weeklyCapacity;
  
  @NotNull
  @PositiveOrZero
  private Integer currentLoad;
  
  @NotBlank
  @Size(max = 1)
  private String status;
}
