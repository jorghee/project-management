package com.cyacompany.projectmanagement_api.dto;

import lombok.Data;

@Data
public class TaskAssignmentResponse {
  private Integer id;
  private String status;
  
  // Aplanamos las relaciones
  private Integer employeeId;
  private String employeeName;
  private Integer taskId;
  private String taskDescription;
}
