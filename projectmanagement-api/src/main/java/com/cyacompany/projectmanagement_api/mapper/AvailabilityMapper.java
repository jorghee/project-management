package com.cyacompany.projectmanagement_api.mapper;

import com.cyacompany.projectmanagement_api.dto.AvailabilityRequest;
import com.cyacompany.projectmanagement_api.dto.AvailabilityResponse;
import com.cyacompany.projectmanagement_api.model.Availability;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AvailabilityMapper {

  // Mapea la entidad a un DTO de respuesta legible
  @Mapping(source = "employee.id", target = "employeeId")
  @Mapping(source = "employee.name", target = "employeeName")
  AvailabilityResponse toResponse(Availability availability);

  // Mapea el DTO de solicitud a la entidad.
  @Mapping(target = "employeeId", ignore = true)
  @Mapping(target = "employee", ignore = true)
  Availability toEntity(AvailabilityRequest availabilityRequest);
}
