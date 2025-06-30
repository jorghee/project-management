package com.cyacompany.projectmanagement_api.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ProjectResponse {
  private Integer id;
  private String name;
  private LocalDate startDate;
  private LocalDate endDate;
  private Double estimatedAmount;
  private Double realAmount;
  private String status;
  
  // Aplanamos las relaciones
  private Integer clientId;
  private String clientName;
  private Integer projectStatusId;
  private String projectStatusDescription;
}
