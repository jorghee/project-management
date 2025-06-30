package com.cyacompany.projectmanagement_api.mapper;

import com.cyacompany.projectmanagement_api.dto.ExperienceLevelDto;
import com.cyacompany.projectmanagement_api.model.ExperienceLevel;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExperienceLevelMapper {
  ExperienceLevelDto toDto(ExperienceLevel entity);
  ExperienceLevel toEntity(ExperienceLevelDto dto);
}
