package com.cyacompany.projectmanagement_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "G2C_DISPONIBILIDAD")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Availability {

  @Id
  @Column(name = "EmpCod")
  private Integer employeeId;

  @OneToOne
  @MapsId
  @JoinColumn(name = "EmpCod")
  private Employee employee;

  @Column(name = "DisEst", length = 1, nullable = false)
  private String availabilityStatus;

  @Column(name = "EstReg", length = 1, nullable = false)
  private String status;
}
