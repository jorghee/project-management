package com.cyacompany.projectmanagement_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "G3Z_TIPO_TAREA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskType {

  @Id
  @Column(name = "TipTarCod")
  private Integer id;

  @Column(name = "TipTarDes", length = 50, nullable = false)
  private String description;

  @Column(name = "EstReg", length = 1, nullable = false)
  private String status;
}
