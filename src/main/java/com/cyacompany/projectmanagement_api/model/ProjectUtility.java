package com.cyacompany.projectmanagement_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "G3D_UTILIDAD_PROYECTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectUtility {

  @Id
  @Column(name = "UtiCod")
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "ProCod", nullable = false)
  private Project project;

  @Column(name = "UtiPorBase", nullable = false)
  private Double basePercentage;

  @ManyToOne
  @JoinColumn(name = "FacTieCod", nullable = false)
  private TimeFactor timeFactor;

  @Column(name = "UtiFacExp", nullable = false)
  private Double experienceFactor;

  @Column(name = "UtiPorFinal", nullable = false)
  private Double finalPercentage;

  @Column(name = "EstReg", length = 1, nullable = false)
  private String status;
}
