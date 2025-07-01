package com.cyacompany.projectmanagement_api.controller;

import com.cyacompany.projectmanagement_api.dto.AvailabilityRequest;
import com.cyacompany.projectmanagement_api.dto.AvailabilityResponse;
import com.cyacompany.projectmanagement_api.mapper.AvailabilityMapper;
import com.cyacompany.projectmanagement_api.model.Availability;
import com.cyacompany.projectmanagement_api.service.AvailabilityService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/availabilities")
public class AvailabilityController {

  private final AvailabilityService service;
  private final AvailabilityMapper mapper;

  public AvailabilityController(AvailabilityService service, AvailabilityMapper mapper) {
    this.service = service;
    this.mapper = mapper;
  }

  /**
   * Obtiene todos los registros de disponibilidad.
   * @return Lista de DTOs de respuesta de Availability.
   */
  @GetMapping
  public ResponseEntity<List<AvailabilityResponse>> getAll() {
    List<AvailabilityResponse> dtoList = service.getAll().stream()
        .map(mapper::toResponse)
        .collect(Collectors.toList());
    return ResponseEntity.ok(dtoList);
  }

  /**
   * Obtiene el estado de disponibilidad para un empleado específico.
   * La clave primaria de Availability es el ID del empleado.
   * @param employeeId El ID del empleado.
   * @return El estado de disponibilidad del empleado.
   */
  @GetMapping("/{employeeId}")
  public ResponseEntity<AvailabilityResponse> getByEmployeeId(@PathVariable Integer employeeId) {
    Availability availability = service.getByEmployeeId(employeeId);
    return ResponseEntity.ok(mapper.toResponse(availability));
  }

  /**
   * Crea o actualiza el estado de disponibilidad para un empleado.
   * Esta operación es idempotente: si la disponibilidad no existe, se crea; si existe, se actualiza.
   * Por eso se usa PUT.
   * @param employeeId El ID del empleado cuya disponibilidad se va a gestionar.
   * @param requestDto Los detalles de la disponibilidad a establecer.
   * @return El estado de disponibilidad creado o actualizado.
   */
  @PutMapping("/{employeeId}")
  public ResponseEntity<AvailabilityResponse> createOrUpdate(
      @PathVariable Integer employeeId,
      @Valid @RequestBody AvailabilityRequest requestDto) {
        
    Availability availabilityDetails = mapper.toEntity(requestDto);
    Availability savedAvailability = service.createOrUpdate(employeeId, availabilityDetails);
    
    // Verificamos si el recurso fue creado (201) o actualizado (200)
    // Para simplificar, siempre devolvemos 200 OK.
    return ResponseEntity.ok(mapper.toResponse(savedAvailability));
  }

  /**
   * Elimina el registro de disponibilidad para un empleado.
   * @param employeeId El ID del empleado cuyo registro de disponibilidad se eliminará.
   * @return Sin contenido (204).
   */
  @DeleteMapping("/{employeeId}")
  public ResponseEntity<Void> delete(@PathVariable Integer employeeId) {
    service.deleteById(employeeId);
    return ResponseEntity.noContent().build();
  }
}
