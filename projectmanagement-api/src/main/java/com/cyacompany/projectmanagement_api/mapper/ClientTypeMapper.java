package com.cyacompany.projectmanagement_api.mapper;

import com.cyacompany.projectmanagement_api.dto.ClientTypeDto;
import com.cyacompany.projectmanagement_api.model.ClientType;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientTypeMapper {
  ClientTypeDto toDto(ClientType entity);
  ClientType toEntity(ClientTypeDto dto);
}
