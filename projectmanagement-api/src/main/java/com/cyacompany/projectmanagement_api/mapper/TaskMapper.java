package com.cyacompany.projectmanagement_api.mapper;

import com.cyacompany.projectmanagement_api.dto.TaskRequest;
import com.cyacompany.projectmanagement_api.dto.TaskResponse;
import com.cyacompany.projectmanagement_api.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TaskMapper {
  TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);
  
  @Mapping(source = "activity.id", target = "activityId")
  @Mapping(source = "activity.name", target = "activityName")
  @Mapping(source = "taskType.id", target = "taskTypeId")
  @Mapping(source = "taskType.description", target = "taskTypeDescription")
  @Mapping(source = "priority.id", target = "priorityId")
  @Mapping(source = "priority.description", target = "priorityDescription")
  TaskResponse toResponse(Task task);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "activity", ignore = true)
  @Mapping(target = "taskType", ignore = true)
  @Mapping(target = "priority", ignore = true)
  Task toEntity(TaskRequest taskRequest);
}
