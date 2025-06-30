package com.cyacompany.projectmanagement_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PriorityDto {

  @NotNull(message = "ID cannot be null")
  @Min(1)
  private Integer id;
  
  @NotBlank(message = "Description cannot be blank")
  @Size(max = 20, message = "Description must be less than 20 characters")
  private String description;
  
  @NotBlank(message = "Status cannot be blank")
  @Size(max = 1, message = "Status must be a single character")
  private String status;
}
