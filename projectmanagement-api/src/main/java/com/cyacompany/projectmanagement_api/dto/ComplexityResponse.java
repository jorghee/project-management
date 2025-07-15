package com.cyacompany.projectmanagement_api.dto;

import lombok.Data;

@Data
public class ComplexityResponse {
  // Aplanamos la clave compuesta y la relación
  private Integer projectUtilityId;
  private String projectName; 
  private Integer utilityFactorId;
  private String utilityFactorDescription;
  private String status;
}
