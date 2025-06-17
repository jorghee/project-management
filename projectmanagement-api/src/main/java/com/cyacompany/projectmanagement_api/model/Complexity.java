package com.cyacompany.projectmanagement_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "G4D_COMPLEJIDAD")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ComplexityId.class)
public class Complexity {

  @Id
  @ManyToOne
  @JoinColumn(name = "UtiCod", nullable = false)
  private ProjectUtility projectUtility;

  @Id
  @ManyToOne
  @JoinColumn(name = "FacUtiCod", nullable = false)
  private UtilityFactor utilityFactor;

  @Column(name = "EstReg", length = 1, nullable = false)
  private String status;
}
