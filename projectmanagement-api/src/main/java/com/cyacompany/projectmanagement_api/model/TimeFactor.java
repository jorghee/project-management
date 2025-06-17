package com.cyacompany.projectmanagement_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "G4Z_FACTOR_TIEMPO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimeFactor {

  @Id
  @Column(name = "FacTieCod")
  private Integer id;

  @Column(name = "FacTieDes", length = 100, nullable = false)
  private String description;

  @Column(name = "FacTieVal", nullable = false)
  private Double value;

  @Column(name = "EstReg", length = 1, nullable = false)
  private String status;
}
