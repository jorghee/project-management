package com.cyacompany.projectmanagement_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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

  @ManyToOne
  @JoinColumn(name = "EmpCod", nullable = false)
  private Employee employee;

  @ManyToOne
  @JoinColumn(name = "TarCod", nullable = false)
  private Task task;

  @Column(name = "AsiFec", nullable = false)
  private LocalDate assignmentDate;

  @Column(name = "EstReg", length = 1, nullable = false)
  private String status;
}
