package com.cyacompany.projectmanagement_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "G1M_ETAPA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Stage {

  @Id
  @Column(name = "EtaCod")
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "ProCod", nullable = false)
  private Project project;

  @Column(name = "EtaNom", length = 100, nullable = false)
  private String name;

  @Column(name = "EtaTieEst")
  private Integer estimatedTime;

  @Column(name = "EtaTieReal")
  private Integer realTime;

  @Column(name = "EstReg", length = 1, nullable = false)
  private String status;
}
