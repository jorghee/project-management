package com.cyacompany.projectmanagement_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProjectUtilityRequest {

  @NotNull(message = "Time Factor ID cannot be null")
  private Integer timeFactorId;

  @NotNull(message = "Experience Factor cannot be null")
  @PositiveOrZero(message = "Experience Factor must be positive or zero")
  private Double experienceFactor;

  @NotNull(message = "Base Percentage cannot be null")
  @PositiveOrZero(message = "Base Percentage must be positive or zero")
  private Double basePercentage;
  
  @NotNull(message = "Final Percentage cannot be null")
  @PositiveOrZero(message = "Final Percentage must be positive or zero")
  private Double finalPercentage;

  @NotBlank(message = "Status cannot be blank")
  @Size(max = 1)
  private String status;
}
