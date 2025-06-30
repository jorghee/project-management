package com.cyacompany.projectmanagement_api.mapper;

import com.cyacompany.projectmanagement_api.dto.TimeFactorDto;
import com.cyacompany.projectmanagement_api.model.TimeFactor;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TimeFactorMapper {
  TimeFactorDto toDto(TimeFactor entity);
  TimeFactor toEntity(TimeFactorDto dto);
}
