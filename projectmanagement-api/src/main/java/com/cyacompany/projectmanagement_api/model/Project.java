package com.cyacompany.projectmanagement_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "G1M_PROYECTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Project {

  @Id
  @Column(name = "ProCod")
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "ProCliCod", nullable = false)
  private Client client;

  @ManyToOne
  @JoinColumn(name = "ProEstProCod", nullable = false)
  private ProjectStatus projectStatus;

  @Column(name = "ProNom", length = 100, nullable = false)
  private String name;

  @Column(name = "ProFecIni", nullable = false)
  private LocalDate startDate;

  @Column(name = "ProFecFin")
  private LocalDate endDate;

  @Column(name = "ProMonEst", precision = 9, scale = 2)
  private Double estimatedAmount;

  @Column(name = "ProMonReal", precision = 9, scale = 2)
  private Integer realAmount;

  @Column(name = "ProEstReg", length = 1, nullable = false)
  private String status;
}
