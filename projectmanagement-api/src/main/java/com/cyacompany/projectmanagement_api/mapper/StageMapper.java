package com.cyacompany.projectmanagement_api.mapper;

import com.cyacompany.projectmanagement_api.dto.StageRequest;
import com.cyacompany.projectmanagement_api.dto.StageResponse;
import com.cyacompany.projectmanagement_api.model.Stage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StageMapper {
  StageMapper INSTANCE = Mappers.getMapper(StageMapper.class);

  @Mapping(source = "project.id", target = "projectId")
  @Mapping(source = "project.name", target = "projectName")
  StageResponse toResponse(Stage stage);

  Stage toEntity(StageRequest stageRequest);
}
