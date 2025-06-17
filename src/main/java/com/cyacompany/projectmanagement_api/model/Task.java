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

  @ManyToOne
  @JoinColumn(name = "ActCod", nullable = false)
  private Activity activity;

  @ManyToOne
  @JoinColumn(name = "TipTarCod", nullable = false)
  private TaskType type;

  @ManyToOne
  @JoinColumn(name = "PriCod", nullable = false)
  private Priority priority;

  @Column(name = "TarDes", length = 100, nullable = false)
  private String description;

  @Column(name = "TarTieEst")
  private Integer estimatedTime;

  @Column(name = "TarTieReal")
  private Integer realTime;

  @Column(name = "TarEst", length = 1, nullable = false)
  private String taskStatus;

  @Column(name = "EstReg", length = 1, nullable = false)
  private String recordStatus;
}
