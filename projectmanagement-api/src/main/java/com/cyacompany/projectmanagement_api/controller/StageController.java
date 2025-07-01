package com.cyacompany.projectmanagement_api.controller;

import com.cyacompany.projectmanagement_api.dto.StageRequest;
import com.cyacompany.projectmanagement_api.dto.StageResponse;
import com.cyacompany.projectmanagement_api.mapper.StageMapper;
import com.cyacompany.projectmanagement_api.model.Stage;
import com.cyacompany.projectmanagement_api.service.StageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/stages")
public class StageController {

  private final StageService service;
  private final StageMapper mapper;

  public StageController(StageService service, StageMapper mapper) {
    this.service = service;
    this.mapper = mapper;
  }

  /**
   * Endpoint para obtener TODAS las etapas sin paginación.
   * Diseñado para la grilla principal del frontend.
   * @return Lista de todos los DTOs de respuesta de Stage.
   */
  @GetMapping("/all")
  public ResponseEntity<List<StageResponse>> getAllStages() {
    List<Stage> stages = service.getAllUnpaged();
    List<StageResponse> dtoList = stages.stream()
      .map(mapper::toResponse)
      .collect(Collectors.toList());
    return ResponseEntity.ok(dtoList);
  }

  // Endpoint para obtener etapas por proyecto
  @GetMapping("/by-project/{projectId}")
  public ResponseEntity<List<StageResponse>> getStagesByProjectId(@PathVariable Integer projectId) {
    List<Stage> stages = service.getByProjectId(projectId);
    List<StageResponse> dtoList = stages.stream()
      .map(mapper::toResponse)
      .collect(Collectors.toList());
    return ResponseEntity.ok(dtoList);
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<StageResponse> getById(@PathVariable Integer id) {
    Stage stage = service.getById(id);
    return ResponseEntity.ok(mapper.toResponse(stage));
  }

  @PostMapping
  public ResponseEntity<StageResponse> create(@Valid @RequestBody StageRequest requestDto) {
    Stage stageToCreate = mapper.toEntity(requestDto);
    Stage newStage = service.create(stageToCreate, requestDto.getProjectId());
    return new ResponseEntity<>(mapper.toResponse(newStage), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<StageResponse> update(@PathVariable Integer id, @Valid @RequestBody StageRequest requestDto) {
    Stage stageDetails = mapper.toEntity(requestDto);
    Stage updatedStage = service.update(id, stageDetails, requestDto.getProjectId());
    return ResponseEntity.ok(mapper.toResponse(updatedStage));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
