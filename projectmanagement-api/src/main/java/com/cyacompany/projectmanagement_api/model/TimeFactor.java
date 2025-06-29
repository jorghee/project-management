package com.cyacompany.projectmanagement_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "G4M_FACTOR_TIEMPO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimeFactor {

  @Id
  @Column(name = "FacTieCod")
  private Integer id;

  @Column(name = "FacTieDes", length = 50, nullable = false)
  private String description;

  @Column(name = "FacTieVal", precision = 4, scale = 2)
  private Double value;

  @Column(name = "FacEstReg", length = 1, nullable = false)
  private String status;
}
