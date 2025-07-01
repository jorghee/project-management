package com.cyacompany.projectmanagement_api.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class TaskExecutionResponse {
  private Integer id;
  private LocalDate executionDate;
  private Integer hours;
  private Integer minutes;
  private String status;
  
  // Aplanamos la relación
  private Integer assignmentId;
}
