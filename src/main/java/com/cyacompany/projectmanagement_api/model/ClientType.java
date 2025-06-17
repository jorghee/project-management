package com.cyacompany.projectmanagement_api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "G1Z_TIPO_CLIENTE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientType {

  @Id
  @Column(name = "TipCliCod")
  private Integer id;

  @Column(name = "TipCliDes", length = 100, nullable = false)
  private String description;

  @Column(name = "EstReg", length = 1, nullable = false)
  private String status;
}
