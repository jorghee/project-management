package com.cyacompany.projectmanagement_api.mapper;

import com.cyacompany.projectmanagement_api.dto.ProjectUtilityRequest;
import com.cyacompany.projectmanagement_api.dto.ProjectUtilityResponse;
import com.cyacompany.projectmanagement_api.model.ProjectUtility;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectUtilityMapper {

  /**
   * Convierte una entidad ProjectUtility a su DTO de respuesta.
   * Mapea los IDs y las descripciones de las entidades relacionadas para aplanar la estructura.
   */
  @Mapping(source = "project.id", target = "projectId")
  @Mapping(source = "project.name", target = "projectName")
  @Mapping(source = "timeFactor.id", target = "timeFactorId")
  @Mapping(source = "timeFactor.description", target = "timeFactorDescription")
  ProjectUtilityResponse toResponse(ProjectUtility projectUtility);

  /**
   * Convierte un DTO de solicitud a una entidad ProjectUtility.
   * Ignora las propiedades que se establecerán manualmente en el servicio
   * (como la entidad Project completa y el ID).
   */
  @Mapping(target = "projectId", ignore = true)
  @Mapping(target = "project", ignore = true)
  @Mapping(target = "timeFactor", ignore = true)
  ProjectUtility toEntity(ProjectUtilityRequest projectUtilityRequest);
}
