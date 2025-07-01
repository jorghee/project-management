package com.cyacompany.projectmanagement_api.dto;

import lombok.Data;

@Data
public class ProjectUtilityResponse {

  private Integer projectId;
  private String projectName;

  private Integer timeFactorId;
  private String timeFactorDescription;
  
  private Double experienceFactor;
  private Double basePercentage;
  private Double finalPercentage;
  private String status;
}
