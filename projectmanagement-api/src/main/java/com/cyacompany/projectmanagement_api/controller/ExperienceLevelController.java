package com.cyacompany.projectmanagement_api.controller;

import com.cyacompany.projectmanagement_api.dto.ExperienceLevelDto;
import com.cyacompany.projectmanagement_api.mapper.ExperienceLevelMapper;
import com.cyacompany.projectmanagement_api.model.ExperienceLevel;
import com.cyacompany.projectmanagement_api.service.ExperienceLevelService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/experience-levels")
public class ExperienceLevelController {

  private final ExperienceLevelService service;
  private final ExperienceLevelMapper mapper;

  public ExperienceLevelController(ExperienceLevelService service, ExperienceLevelMapper mapper) {
    this.service = service;
    this.mapper = mapper;
  }

  @GetMapping
  public ResponseEntity<List<ExperienceLevelDto>> getAll() {
    List<ExperienceLevelDto> dtoList = service.getAll().stream()
      .map(mapper::toDto)
      .collect(Collectors.toList());
    return ResponseEntity.ok(dtoList);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ExperienceLevelDto> getById(@PathVariable Integer id) {
    ExperienceLevel experienceLevel = service.getById(id);
    return ResponseEntity.ok(mapper.toDto(experienceLevel));
  }

  @PostMapping
  public ResponseEntity<ExperienceLevelDto> create(@Valid @RequestBody ExperienceLevelDto dto) {
    ExperienceLevel newExperienceLevel = service.save(mapper.toEntity(dto));
    return new ResponseEntity<>(mapper.toDto(newExperienceLevel), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ExperienceLevelDto> update(@PathVariable Integer id, @Valid @RequestBody ExperienceLevelDto dto) {
    dto.setId(id);
    ExperienceLevel updatedExperienceLevel = service.save(mapper.toEntity(dto));
    return ResponseEntity.ok(mapper.toDto(updatedExperienceLevel));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    service.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
