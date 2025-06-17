package com.cyacompany.projectmanagement_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "G1M_PROYECTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Project {

  @Id
  @Column(name = "ProCod")
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "CliCod", nullable = false)
  private Client client;

  @ManyToOne
  @JoinColumn(name = "EstProCod", nullable = false)
  private ProjectStatus status;

  @Column(name = "ProNom", length = 100, nullable = false)
  private String name;

  @Column(name = "ProFecIni", nullable = false)
  private LocalDate startDate;

  @Column(name = "ProDurEst")
  private Integer estimatedDuration;

  @Column(name = "ProDurReal")
  private Integer realDuration;

  @Column(name = "EstReg", length = 1, nullable = false)
  private String recordStatus;
}
