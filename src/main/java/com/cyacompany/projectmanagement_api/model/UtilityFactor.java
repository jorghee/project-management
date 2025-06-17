package com.cyacompany.projectmanagement_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "G4Z_FACTOR_UTILIDAD")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UtilityFactor {

  @Id
  @Column(name = "FacUtiCod")
  private Integer id;

  @Column(name = "FacUtiDes", length = 100, nullable = false)
  private String description;

  @Column(name = "EstReg", length = 1, nullable = false)
  private String status;
}
