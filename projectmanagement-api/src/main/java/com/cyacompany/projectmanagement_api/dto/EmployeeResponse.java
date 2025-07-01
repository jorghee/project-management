package com.cyacompany.projectmanagement_api.dto;

import lombok.Data;

@Data
public class EmployeeResponse {
  private Integer id;
  private String name;
  private String status;
  
  // Aplanamos las relaciones
  private Integer positionId;
  private String positionDescription;
  private Integer experienceLevelId;
  private String experienceLevelDescription;
}
