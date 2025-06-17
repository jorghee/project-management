package com.cyacompany.projectmanagement_api.model;

import jakarta.persistence.*;
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

  @ManyToOne
  @JoinColumn(name = "TipCliCod", nullable = false)
  private ClientType clientType;

  @Column(name = "CliNom", length = 100, nullable = false)
  private String name;

  @Column(name = "CliDir", length = 150)
  private String address;

  @Column(name = "CliTel", length = 20)
  private String phone;

  @Column(name = "EstReg", length = 1, nullable = false)
  private String status;
}
