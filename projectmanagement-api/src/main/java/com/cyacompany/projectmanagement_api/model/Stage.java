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

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "EtaProCod", nullable = false)
  private Project project;

  @Column(name = "EtaNom", length = 50, nullable = false)
  private String name;

  @Column(name = "EtaTieEst")
  private Integer estimatedTime;

  @Column(name = "EtaEstReg", length = 1, nullable = false)
  private String status;
}
