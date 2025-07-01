package com.cyacompany.projectmanagement_api.dto;

import lombok.Data;

@Data
public class AvailabilityResponse {
  private Integer employeeId;
  private String employeeName;
  private String availabilityStatus;
  private Integer weeklyCapacity;
  private Integer currentLoad;
  private String status;
}
