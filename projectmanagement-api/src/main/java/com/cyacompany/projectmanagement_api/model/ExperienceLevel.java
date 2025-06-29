package com.cyacompany.projectmanagement_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "G2M_NIVEL_EXPERIENCIA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceLevel {

  @Id
  @Column(name = "ExpCod")
  private Integer id;

  @Column(name = "ExpDes", length = 20, nullable = false)
  private String description;

  @Column(name = "ExpVal", precision = 4, scale = 2)
  private Double value;

  @Column(name = "ExpEstReg", length = 1, nullable = false)
  private String status;
}
