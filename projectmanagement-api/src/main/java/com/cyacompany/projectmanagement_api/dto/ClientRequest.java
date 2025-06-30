package com.cyacompany.projectmanagement_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ClientRequest {
  @NotBlank(message = "Name cannot be blank")
  @Size(max = 100, message = "Name must be less than 100 characters")
  private String name;

  private LocalDate entryDate;
  
  private LocalDate terminationDate;

  @Size(max = 1)
  private String clientStatus;
  
  @NotBlank
  @Size(max = 1)
  private String status;
  
  @NotNull(message = "Client Type ID cannot be null")
  private Integer clientTypeId;
}
