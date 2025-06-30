package com.cyacompany.projectmanagement_api.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ClientResponse {
  private Integer id;
  private String name;
  private LocalDate entryDate;
  private LocalDate terminationDate;
  private String clientStatus;
  private String status;
  
  // Aplanamos la relación
  private Integer clientTypeId;
  private String clientTypeDescription;
}
