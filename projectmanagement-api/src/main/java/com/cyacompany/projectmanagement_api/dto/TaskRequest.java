package com.cyacompany.projectmanagement_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TaskRequest {
  @NotBlank
  @Size(max = 100)
  private String description;
  
  @PositiveOrZero
  private Integer estimatedTime;
  
  @PositiveOrZero
  private Integer realTime;
  
  @Size(max = 1)
  private String taskStatus;
  
  @NotBlank
  @Size(max = 1)
  private String status;
  
  @NotNull
  private Integer activityId;
  
  @NotNull
  private Integer taskTypeId;
  
  @NotNull
  private Integer priorityId;
}
