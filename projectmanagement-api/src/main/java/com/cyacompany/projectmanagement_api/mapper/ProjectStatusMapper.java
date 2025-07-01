
package com.cyacompany.projectmanagement_api.mapper;

import com.cyacompany.projectmanagement_api.dto.ProjectStatusDto;
import com.cyacompany.projectmanagement_api.model.ProjectStatus;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectStatusMapper {
  ProjectStatusDto toDto(ProjectStatus entity);
  ProjectStatus toEntity(ProjectStatusDto dto);
}

