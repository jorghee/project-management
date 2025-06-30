package com.cyacompany.projectmanagement_api.mapper;

import com.cyacompany.projectmanagement_api.dto.PositionDto;
import com.cyacompany.projectmanagement_api.model.Position;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PositionMapper {
  PositionDto toDto(Position entity);
  Position toEntity(PositionDto dto);
}
