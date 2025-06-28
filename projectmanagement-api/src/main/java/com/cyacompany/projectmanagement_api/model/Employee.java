package com.cyacompany.projectmanagement_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "G2M_EMPLEADO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

  @Id
  @Column(name = "EmpCod")
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "EmpCarCod", nullable = false)
  private Position position;

  @ManyToOne
  @JoinColumn(name = "EmpNivExpCod", nullable = false)
  private ExperienceLevel experienceLevel;

  @Column(name = "EmpNom", length = 100, nullable = false)
  private String name;

  @Column(name = "EmpEstReg", length = 1, nullable = false)
  private String status;
}
