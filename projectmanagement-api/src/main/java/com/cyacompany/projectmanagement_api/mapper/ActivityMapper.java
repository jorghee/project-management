package com.cyacompany.projectmanagement_api.mapper;

import com.cyacompany.projectmanagement_api.dto.ActivityRequest;
import com.cyacompany.projectmanagement_api.dto.ActivityResponse;
import com.cyacompany.projectmanagement_api.model.Activity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ActivityMapper {
  ActivityMapper INSTANCE = Mappers.getMapper(ActivityMapper.class);

  @Mapping(source = "stage.id", target = "stageId")
  @Mapping(source = "stage.name", target = "stageName")
  @Mapping(source = "responsible.id", target = "responsibleId")
  @Mapping(source = "responsible.name", target = "responsibleName")
  ActivityResponse toResponse(Activity activity);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "stage", ignore = true)
  @Mapping(target = "responsible", ignore = true)
  Activity toEntity(ActivityRequest activityRequest);
}
