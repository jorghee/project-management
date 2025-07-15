package com.cyacompany.projectmanagement_api.controller;

import com.cyacompany.projectmanagement_api.dto.ComplexityResponse;
import com.cyacompany.projectmanagement_api.mapper.ComplexityMapper;
import com.cyacompany.projectmanagement_api.service.ComplexityService;
import com.cyacompany.projectmanagement_api.model.Complexity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
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

  /**
   * Crea una nueva relación de complejidad entre un proyecto y un factor de utilidad.
   * @param projectId El ID del proyecto (ProjectUtility).
   * @param factorId El ID del factor de utilidad.
   * @return La relación creada.
   */
  @PostMapping("/{projectId}/{factorId}")
  public ResponseEntity<ComplexityResponse> createComplexity(
      @PathVariable Integer projectId, 
      @PathVariable Integer factorId) {
    
    Complexity details = new Complexity();
    details.setStatus("A");
    
    Complexity newComplexity = service.create(details, projectId, factorId);
    return new ResponseEntity<>(mapper.toResponse(newComplexity), HttpStatus.CREATED);
  }

  /**
   * Elimina una relación de complejidad existente.
   * @param projectId El ID del proyecto (ProjectUtility).
   * @param factorId El ID del factor de utilidad.
   * @return Sin contenido (204).
   */
  @DeleteMapping("/{projectId}/{factorId}")
  public ResponseEntity<Void> deleteComplexity(
      @PathVariable Integer projectId,
      @PathVariable Integer factorId) {
        
    service.delete(projectId, factorId);
    return ResponseEntity.noContent().build();
  }
}
