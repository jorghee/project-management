package com.cyacompany.projectmanagement_api.mapper;

import com.cyacompany.projectmanagement_api.dto.TaskTypeDto;
import com.cyacompany.projectmanagement_api.model.TaskType;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskTypeMapper {
  TaskTypeDto toDto(TaskType entity);
  TaskType toEntity(TaskTypeDto dto);
}
