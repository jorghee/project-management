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
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ComUtiProCod", referencedColumnName = "UtiProCod")
  private ProjectUtility projectUtility;

  @Id
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ComFacUtiCod", referencedColumnName = "FacUtiCod")
  private UtilityFactor utilityFactor;

  @Column(name = "ComEstReg", length = 1, nullable = false)
  private String status;
}
