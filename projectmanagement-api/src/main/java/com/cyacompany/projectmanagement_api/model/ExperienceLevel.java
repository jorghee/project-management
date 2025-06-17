package com.cyacompany.projectmanagement_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "G4Z_NIVEL_EXPERIENCIA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceLevel {

  @Id
  @Column(name = "ExpCod")
  private Integer id;

  @Column(name = "ExpDes", length = 100, nullable = false)
  private String description;

  @Column(name = "ExpVal", nullable = false)
  private Integer value;

  @Column(name = "EstReg", length = 1, nullable = false)
  private String status;
}
