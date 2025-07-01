package com.cyacompany.projectmanagement_api.mapper;

import com.cyacompany.projectmanagement_api.dto.UtilityFactorDto;
import com.cyacompany.projectmanagement_api.model.UtilityFactor;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UtilityFactorMapper {
  UtilityFactorDto toDto(UtilityFactor entity);
  UtilityFactor toEntity(UtilityFactorDto dto);
}
