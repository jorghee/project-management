package com.cyacompany.projectmanagement_api.mapper;

import com.cyacompany.projectmanagement_api.dto.ProjectRequest;
import com.cyacompany.projectmanagement_api.dto.ProjectResponse;
import com.cyacompany.projectmanagement_api.model.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
  ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

  @Mapping(source = "client.id", target = "clientId")
  @Mapping(source = "client.name", target = "clientName")
  @Mapping(source = "projectStatus.id", target = "projectStatusId")
  @Mapping(source = "projectStatus.description", target = "projectStatusDescription")
  ProjectResponse toResponse(Project project);

  Project toEntity(ProjectRequest projectRequest);
}
