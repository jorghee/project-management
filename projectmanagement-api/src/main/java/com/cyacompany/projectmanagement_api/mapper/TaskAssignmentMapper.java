package com.cyacompany.projectmanagement_api.mapper;

import com.cyacompany.projectmanagement_api.dto.TaskAssignmentRequest;
import com.cyacompany.projectmanagement_api.dto.TaskAssignmentResponse;
import com.cyacompany.projectmanagement_api.model.TaskAssignment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskAssignmentMapper {
  @Mapping(source = "employee.id", target = "employeeId")
  @Mapping(source = "employee.name", target = "employeeName")
  @Mapping(source = "task.id", target = "taskId")
  @Mapping(source = "task.description", target = "taskDescription")
  TaskAssignmentResponse toResponse(TaskAssignment assignment);

  @Mapping(target = "employee", ignore = true)
  @Mapping(target = "task", ignore = true)
  TaskAssignment toEntity(TaskAssignmentRequest request);
}
