package com.cyacompany.projectmanagement_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "G3M_TAREA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {

  @Id
  @Column(name = "TarCod")
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "TarActCod", nullable = false)
  private Activity activity;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "TarTipTarCod", nullable = false)
  private TaskType taskType;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "TarPriCod", nullable = false)
  private Priority priority;

  @Column(name = "TarDes", length = 100, nullable = false)
  private String description;

  @Column(name = "TarTieEst")
  private Integer estimatedTime;

  @Column(name = "TarTieReal")
  private Integer realTime;

  @Column(name = "TarEst", length = 1, nullable = false)
  private String taskStatus;

  @Column(name = "TarEstReg", length = 1, nullable = false)
  private String status;
}
