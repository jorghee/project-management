package com.cyacompany.projectmanagement_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "G1Z_ESTADO_PROYECTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectStatus {

  @Id
  @Column(name = "EstProCod")
  private Integer id;

  @Column(name = "EstProDes", length = 100, nullable = false)
  private String description;

  @Column(name = "EstReg", length = 1, nullable = false)
  private String status;
}
