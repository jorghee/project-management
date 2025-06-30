package com.cyacompany.projectmanagement_api.dto;

import lombok.Data;

@Data
public class ActivityResponse {
  private Integer id;
  private String name;
  private Integer estimatedTime;
  private Integer realTime;
  private String status;
  
  // Aplanamos las relaciones
  private Integer stageId;
  private String stageName;
  private Integer responsibleId;
  private String responsibleName;
}
