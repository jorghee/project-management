package com.cyacompany.projectmanagement_api.mapper;

import com.cyacompany.projectmanagement_api.dto.ClientRequest;
import com.cyacompany.projectmanagement_api.dto.ClientResponse;
import com.cyacompany.projectmanagement_api.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClientMapper {
  ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

  // Mapea los campos de la entidad Client y su relación ClientType al DTO de respuesta
  @Mapping(source = "clientType.id", target = "clientTypeId")
  @Mapping(source = "clientType.description", target = "clientTypeDescription")
  ClientResponse toResponse(Client client);

  // Mapea el DTO de solicitud a la entidad Client. Las relaciones se manejan en el servicio.
  Client toEntity(ClientRequest clientRequest);
}
