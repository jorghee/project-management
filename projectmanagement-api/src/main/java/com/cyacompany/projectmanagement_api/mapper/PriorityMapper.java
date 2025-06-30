package com.cyacompany.projectmanagement_api.mapper;

import com.cyacompany.projectmanagement_api.dto.PriorityDto;
import com.cyacompany.projectmanagement_api.model.Priority;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriorityMapper {
  PriorityDto toDto(Priority entity);
  Priority toEntity(PriorityDto dto);
}
