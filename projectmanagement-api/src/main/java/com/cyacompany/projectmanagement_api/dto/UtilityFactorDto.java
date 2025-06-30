package com.cyacompany.projectmanagement_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UtilityFactorDto {
  private Integer id;

  @NotBlank(message = "Description cannot be blank")
  @Size(max = 50, message = "Description must be less than 50 characters")
  private String description;

  @NotNull(message = "Value cannot be null")
  @Positive(message = "Value must be positive")
  private Double value;

  @NotBlank(message = "Status cannot be blank")
  @Size(max = 1, message = "Status must be a single character")
  private String status;
}
