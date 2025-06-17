package com.cyacompany.projectmanagement_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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
  @JoinColumn(name = "CarCod", nullable = false)
  private Position position;

  @ManyToOne
  @JoinColumn(name = "ExpCod", nullable = false)
  private ExperienceLevel experienceLevel;

  @Column(name = "EmpNom", length = 100, nullable = false)
  private String name;

  @Column(name = "EmpDni", length = 20, nullable = false, unique = true)
  private String document;

  @Column(name = "EmpTel", length = 20)
  private String phone;

  @Column(name = "EmpFecIng", nullable = false)
  private LocalDate hireDate;

  @Column(name = "EstReg", length = 1, nullable = false)
  private String status;
}
