package com.cyacompany.projectmanagement_api.dto;

import lombok.Data;

@Data
public class TaskResponse {
  private Integer id;
  private String description;
  private Integer estimatedTime;
  private Integer realTime;
  private String taskStatus;
  private String status;
  
  // Aplanamos las relaciones
  private Integer activityId;
  private String activityName;
  private Integer taskTypeId;
  private String taskTypeDescription;
  private Integer priorityId;
  private String priorityDescription;
}
