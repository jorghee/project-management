package com.cyacompany.projectmanagement_api.dto;

import lombok.Data;

@Data
public class StageResponse {
  private Integer id;
  private String name;
  private Integer estimatedTime;
  private Integer realTime;
  private String status;
  
  // Aplanamos la relación
  private Integer projectId;
  private String projectName;
}
