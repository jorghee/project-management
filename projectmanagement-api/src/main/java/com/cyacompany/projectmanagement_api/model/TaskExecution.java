package com.cyacompany.projectmanagement_api.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;

@Entity
@Table(name = "G2H_EJECUCION_TAREA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskExecution {

  @Id
  @Column(name = "EjeCod")
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "EjeAsiCod", nullable = false)
  private TaskAssignment assignment;

  @Column(name = "EjeFec", nullable = false)
  private LocalDate executionDate;

  @Column(name = "EjeHor", nullable = false)
  private Integer hours;

  @Column(name = "EjeMin", nullable = false)
  private Integer minutes;

  @Column(name = "EjeEstReg", length = 1, nullable = false)
  private String status;
}
