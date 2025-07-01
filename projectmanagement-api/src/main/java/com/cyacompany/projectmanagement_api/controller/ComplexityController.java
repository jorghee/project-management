package com.cyacompany.projectmanagement_api.controller;

import com.cyacompany.projectmanagement_api.dto.ComplexityResponse;
import com.cyacompany.projectmanagement_api.mapper.ComplexityMapper;
import com.cyacompany.projectmanagement_api.service.ComplexityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/complexities")
public class ComplexityController {

  private final ComplexityService service;
  private final ComplexityMapper mapper;

  public ComplexityController(ComplexityService service, ComplexityMapper mapper) {
    this.service = service;
    this.mapper = mapper;
  }

  /**
   * Endpoint para obtener todas las relaciones de complejidad.
   * Útil para vistas de auditoría general.
   */
  @GetMapping
  public ResponseEntity<List<ComplexityResponse>> getAll() {
    List<ComplexityResponse> dtoList = service.getAll().stream()
      .map(mapper::toResponse)
      .collect(Collectors.toList());
    return ResponseEntity.ok(dtoList);
  }

  /**
   * Endpoint para obtener todas las complejidades (factores) de un proyecto específico.
   * @param projectId El ID del proyecto.
   * @return Lista de factores de utilidad para ese proyecto.
   */
  @GetMapping("/by-project/{projectId}")
  public ResponseEntity<List<ComplexityResponse>> getByProjectId(@PathVariable Integer projectId) {
    List<ComplexityResponse> dtoList = service.getByProjectId(projectId).stream()
      .map(mapper::toResponse)
      .collect(Collectors.toList());
    return ResponseEntity.ok(dtoList);
  }
  
  /**
   * Endpoint para obtener todos los proyectos asociados a un factor de utilidad específico.
   * @param factorId El ID del factor de utilidad.
   * @return Lista de proyectos que usan ese factor.
   */
  @GetMapping("/by-factor/{factorId}")
  public ResponseEntity<List<ComplexityResponse>> getByFactorId(@PathVariable Integer factorId) {
    List<ComplexityResponse> dtoList = service.getByFactorId(factorId).stream()
      .map(mapper::toResponse)
      .collect(Collectors.toList());
    return ResponseEntity.ok(dtoList);
  }
}
