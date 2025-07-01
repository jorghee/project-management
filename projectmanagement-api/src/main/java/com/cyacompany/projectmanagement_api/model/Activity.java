package com.cyacompany.projectmanagement_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "G1M_ACTIVIDAD")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Activity {

  @Id
  @Column(name = "ActCod")
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ActEtaCod", nullable = false)
  private Stage stage;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ActEmpCod", nullable = false)
  private Employee responsible;

  @Column(name = "ActNom", length = 80, nullable = false)
  private String name;

  @Column(name = "ActTieEst")
  private Integer estimatedTime;

  @Column(name = "ActTieReal")
  private Integer realTime;

  @Column(name = "ActEstReg", length = 1, nullable = false)
  private String status;
}
