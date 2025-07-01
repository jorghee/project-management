package com.cyacompany.projectmanagement_api.mapper;

import com.cyacompany.projectmanagement_api.dto.EmployeeRequest;
import com.cyacompany.projectmanagement_api.dto.EmployeeResponse;
import com.cyacompany.projectmanagement_api.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
  EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

  @Mapping(source = "position.id", target = "positionId")
  @Mapping(source = "position.description", target = "positionDescription")
  @Mapping(source = "experienceLevel.id", target = "experienceLevelId")
  @Mapping(source = "experienceLevel.description", target = "experienceLevelDescription")
  EmployeeResponse toResponse(Employee employee);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "position", ignore = true)
  @Mapping(target = "experienceLevel", ignore = true)
  Employee toEntity(EmployeeRequest employeeRequest);
}
