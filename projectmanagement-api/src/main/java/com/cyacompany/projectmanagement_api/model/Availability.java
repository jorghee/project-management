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
  @Column(name = "DisEmpCod")
  private Integer employeeId;

  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  @JoinColumn(name = "DisEmpCod")
  private Employee employee;

  @Column(name = "DisEst", length = 1, nullable = false)
  private String availabilityStatus;

  @Column(name = "DisCapSem")
  private Integer weeklyCapacity;

  @Column(name = "DisCarAct")
  private Integer currentLoad;

  @Column(name = "DisEstReg", length = 1, nullable = false)
  private String status;
}
