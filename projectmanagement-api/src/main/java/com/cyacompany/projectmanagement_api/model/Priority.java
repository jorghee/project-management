package com.cyacompany.projectmanagement_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "G3Z_PRIORIDAD")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Priority {

  @Id
  @Column(name = "PriCod")
  private Integer id;

  @Column(name = "PriDes", length = 100, nullable = false)
  private String description;

  @Column(name = "EstReg", length = 1, nullable = false)
  private String status;
}
