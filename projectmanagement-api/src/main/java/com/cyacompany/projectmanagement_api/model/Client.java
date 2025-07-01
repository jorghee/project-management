package com.cyacompany.projectmanagement_api.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;

@Entity
@Table(name = "G1M_CLIENTE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {

  @Id
  @Column(name = "CliCod")
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "CliTipCliCod", nullable = false)
  private ClientType clientType;

  @Column(name = "CliNom", length = 100, nullable = false)
  private String name;

  @Column(name = "CliFecIng")
  private LocalDate registrationDate;

  @Column(name = "CliFecCse")
  private LocalDate terminationDate;

  @Column(name = "CliEst", length = 1)
  private String clientStatus;

  @Column(name = "CliEstReg", length = 1, nullable = false)
  private String status;
}
