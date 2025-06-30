package com.cyacompany.projectmanagement_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "G3D_UTILIDAD_PROYECTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectUtility {

  @Id
  @Column(name = "UtiProCod")
  private Integer projectId;

  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  @JoinColumn(name = "UtiProCod")
  private Project project;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "UtiFacTieCod", nullable = false)
  private TimeFactor timeFactor;

  @Column(name = "UtiFacExp", nullable = false, precision = 4, scale = 2)
  private BigDecimal experienceFactor;

  @Column(name = "UtiPorBase", nullable = false, precision = 5, scale = 2)
  private BigDecimal basePercentage;

  @Column(name = "UtiPorFin", nullable = false, precision = 5, scale = 2)
  private BigDecimal finalPercentage;

  @Column(name = "UtiEstReg", length = 1, nullable = false)
  private String status;
}
