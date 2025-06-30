package com.cyacompany.projectmanagement_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ActivityRequest {
  @NotBlank
  @Size(max = 80)
  private String name;

  @PositiveOrZero
  private Integer estimatedTime;
  
  @PositiveOrZero
  private Integer realTime;
  
  @NotBlank
  @Size(max = 1)
  private String status;
  
  @NotNull
  private Integer stageId;

  @NotNull
  private Integer responsibleId; // Employee ID
}
