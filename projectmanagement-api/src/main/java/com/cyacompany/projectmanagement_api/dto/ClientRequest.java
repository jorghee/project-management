package com.cyacompany.projectmanagement_api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ClientRequest {

  @NotNull(message = "Project ID cannot be null")
  @Min(1)
  private Integer id; 

  @NotBlank(message = "Name cannot be blank")
  @Size(max = 100, message = "Name must be less than 100 characters")
  private String name;

  @NotNull
  private LocalDate registrationDate;
  
  private LocalDate terminationDate;

  @Size(max = 1)
  private String clientStatus;
  
  @NotBlank
  @Size(max = 1)
  private String status;
  
  @NotNull(message = "Client Type ID cannot be null")
  private Integer clientTypeId;
}
