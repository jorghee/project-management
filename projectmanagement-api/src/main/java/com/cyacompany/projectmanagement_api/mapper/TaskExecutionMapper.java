package com.cyacompany.projectmanagement_api.mapper;

import com.cyacompany.projectmanagement_api.dto.TaskExecutionRequest;
import com.cyacompany.projectmanagement_api.dto.TaskExecutionResponse;
import com.cyacompany.projectmanagement_api.model.TaskExecution;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskExecutionMapper {
  @Mapping(source = "assignment.id", target = "assignmentId")
  TaskExecutionResponse toResponse(TaskExecution execution);
  
  @Mapping(target = "assignment", ignore = true)
  TaskExecution toEntity(TaskExecutionRequest request);
}
