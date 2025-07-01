package com.cyacompany.projectmanagement_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "G2T_ASIGNACION_TAREA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskAssignment {

  @Id
  @Column(name = "AsiCod")
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "AsiEmpCod", nullable = false)
  private Employee employee;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "AsiTarCod", nullable = false)
  private Task task;

  @Column(name = "AsiEstReg", length = 1, nullable = false)
  private String status;
}
