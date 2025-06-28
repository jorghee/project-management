package com.cyacompany.projectmanagement_api.model;

import lombok.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ComplexityId implements Serializable {

  private Integer projectUtility;
  private Integer utilityFactor;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ComplexityId that = (ComplexityId) o;
    return Objects.equals(projectUtility, that.projectUtility) &&
           Objects.equals(utilityFactor, that.utilityFactor);
  }

  @Override
  public int hashCode() {
    return Objects.hash(projectUtility, utilityFactor);
  }
}
