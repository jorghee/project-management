package com.cyacompany.projectmanagement_api.mapper;

import com.cyacompany.projectmanagement_api.dto.ComplexityResponse;
import com.cyacompany.projectmanagement_api.model.Complexity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ComplexityMapper {

  // Mapea la entidad a un DTO de respuesta legible
  @Mapping(source = "projectUtility.projectId", target = "projectUtilityId")
  @Mapping(source = "utilityFactor.id", target = "utilityFactorId")
  @Mapping(source = "utilityFactor.description", target = "utilityFactorDescription")
  ComplexityResponse toResponse(Complexity complexity);
}
