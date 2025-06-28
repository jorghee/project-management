package com.cyacompany.projectmanagement_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "G2Z_CARGO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Position {

  @Id
  @Column(name = "CarCod")
  private Integer id;

  @Column(name = "CarDes", length = 100, nullable = false)
  private String description;

  @Column(name = "CarCosHor", precision = 6, scale = 2)
  private Double costPerHour;

  @Column(name = "CarEstReg", length = 1, nullable = false)
  private String status;
}
