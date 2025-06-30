package com.cyacompany.projectmanagement_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ProjectRequest {
  @NotBlank
  @Size(max = 100)
  private String name;

  @NotNull
  private LocalDate startDate;
  
  private LocalDate endDate;
  
  @PositiveOrZero
  private Double estimatedAmount;
  
  @PositiveOrZero
  private Double realAmount;

  @NotBlank
  @Size(max = 1)
  private String status;

  @NotNull
  private Integer clientId;

  @NotNull
  private Integer projectStatusId;
}
