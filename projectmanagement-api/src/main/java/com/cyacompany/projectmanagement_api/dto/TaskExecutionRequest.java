package com.cyacompany.projectmanagement_api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDate;

@Data
public class TaskExecutionRequest {

  @NotNull(message = "Project ID cannot be null")
  @Min(1)
  private Integer id; 

  @NotNull
  private LocalDate executionDate;
  
  @NotNull
  @PositiveOrZero
  private Integer hours;
  
  @NotNull
  @PositiveOrZero
  private Integer minutes;
  
  @NotBlank
  @Size(max = 1)
  private String status;
  
  @NotNull
  private Integer assignmentId;
}
