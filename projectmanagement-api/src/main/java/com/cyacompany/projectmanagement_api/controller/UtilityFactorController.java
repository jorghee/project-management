package com.cyacompany.projectmanagement_api.controller;

import com.cyacompany.projectmanagement_api.dto.UtilityFactorDto;
import com.cyacompany.projectmanagement_api.mapper.UtilityFactorMapper;
import com.cyacompany.projectmanagement_api.model.UtilityFactor;
import com.cyacompany.projectmanagement_api.service.UtilityFactorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/utility-factors")
public class UtilityFactorController {

  private final UtilityFactorService service;
  private final UtilityFactorMapper mapper;

  public UtilityFactorController(UtilityFactorService service, UtilityFactorMapper mapper) {
    this.service = service;
    this.mapper = mapper;
  }

  @GetMapping
  public ResponseEntity<List<UtilityFactorDto>> getAll() {
    List<UtilityFactorDto> dtoList = service.getAll().stream()
      .map(mapper::toDto)
      .collect(Collectors.toList());
    return ResponseEntity.ok(dtoList);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UtilityFactorDto> getById(@PathVariable Integer id) {
    UtilityFactor utilityFactor = service.getById(id);
    return ResponseEntity.ok(mapper.toDto(utilityFactor));
  }

  @PostMapping
  public ResponseEntity<UtilityFactorDto> create(@Valid @RequestBody UtilityFactorDto dto) {
    UtilityFactor newUtilityFactor = service.save(mapper.toEntity(dto));
    return new ResponseEntity<>(mapper.toDto(newUtilityFactor), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<UtilityFactorDto> update(@PathVariable Integer id, @Valid @RequestBody UtilityFactorDto dto) {
    dto.setId(id);
    UtilityFactor updatedUtilityFactor = service.save(mapper.toEntity(dto));
    return ResponseEntity.ok(mapper.toDto(updatedUtilityFactor));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    service.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
