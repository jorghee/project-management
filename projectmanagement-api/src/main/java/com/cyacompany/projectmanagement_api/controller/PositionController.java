package com.cyacompany.projectmanagement_api.controller;

import com.cyacompany.projectmanagement_api.dto.PositionDto;
import com.cyacompany.projectmanagement_api.mapper.PositionMapper;
import com.cyacompany.projectmanagement_api.model.Position;
import com.cyacompany.projectmanagement_api.service.PositionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/positions")
public class PositionController {

  private final PositionService service;
  private final PositionMapper mapper;

  public PositionController(PositionService service, PositionMapper mapper) {
    this.service = service;
    this.mapper = mapper;
  }

  @GetMapping
  public ResponseEntity<List<PositionDto>> getAll() {
    List<PositionDto> dtoList = service.getAll().stream()
      .map(mapper::toDto)
      .collect(Collectors.toList());
    return ResponseEntity.ok(dtoList);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PositionDto> getById(@PathVariable Integer id) {
    Position position = service.getById(id);
    return ResponseEntity.ok(mapper.toDto(position));
  }

  @PostMapping
  public ResponseEntity<PositionDto> create(@Valid @RequestBody PositionDto dto) {
    Position newPosition = service.save(mapper.toEntity(dto));
    return new ResponseEntity<>(mapper.toDto(newPosition), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<PositionDto> update(@PathVariable Integer id, @Valid @RequestBody PositionDto dto) {
    dto.setId(id);
    Position updatedPosition = service.save(mapper.toEntity(dto));
    return ResponseEntity.ok(mapper.toDto(updatedPosition));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
