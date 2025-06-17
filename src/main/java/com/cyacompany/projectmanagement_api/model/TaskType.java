package com.cyacompany.projectmanagement_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "G3Z_TIPOTAREA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskType {

  @Id
  @Column(name = "TipTarCod")
  private Integer id;

  @Column(name = "TipTarDes", length = 100, nullable = false)
  private String description;

  @Column(name = "EstReg", length = 1, nullable = false)
  private String status;
}
