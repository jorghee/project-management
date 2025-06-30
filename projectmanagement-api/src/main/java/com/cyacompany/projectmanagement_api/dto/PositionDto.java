package com.cyacompany.projectmanagement_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PositionDto {
  private Integer id;

  @NotBlank(message = "Description cannot be blank")
  @Size(max = 50, message = "Description must be less than 50 characters")
  private String description;

  @NotNull(message = "Cost per hour cannot be null")
  @PositiveOrZero(message = "Cost per hour must be a positive number or zero")
  private Double costPerHour;
  
  @NotBlank(message = "Status cannot be blank")
  @Size(max = 1, message = "Status must be a single character")
  private String status;
}
