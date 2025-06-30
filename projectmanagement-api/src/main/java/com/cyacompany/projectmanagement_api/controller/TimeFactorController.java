package com.cyacompany.projectmanagement_api.controller;

import com.cyacompany.projectmanagement_api.dto.TimeFactorDto;
import com.cyacompany.projectmanagement_api.mapper.TimeFactorMapper;
import com.cyacompany.projectmanagement_api.model.TimeFactor;
import com.cyacompany.projectmanagement_api.service.TimeFactorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/time-factors")
public class TimeFactorController {

  private final TimeFactorService service;
  private final TimeFactorMapper mapper;

  public TimeFactorController(TimeFactorService service, TimeFactorMapper mapper) {
    this.service = service;
    this.mapper = mapper;
  }

  @GetMapping
  public ResponseEntity<List<TimeFactorDto>> getAll() {
    List<TimeFactorDto> dtoList = service.getAll().stream()
      .map(mapper::toDto)
      .collect(Collectors.toList());
    return ResponseEntity.ok(dtoList);
  }

  @GetMapping("/{id}")
  public ResponseEntity<TimeFactorDto> getById(@PathVariable Integer id) {
    TimeFactor timeFactor = service.getById(id);
    return ResponseEntity.ok(mapper.toDto(timeFactor));
  }

  @PostMapping
  public ResponseEntity<TimeFactorDto> create(@Valid @RequestBody TimeFactorDto dto) {
    TimeFactor newTimeFactor = service.create(mapper.toEntity(dto));
    return new ResponseEntity<>(mapper.toDto(newTimeFactor), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<TimeFactorDto> update(@PathVariable Integer id, @Valid @RequestBody TimeFactorDto dto) {
    TimeFactor updatedTimeFactor = service.update(id, mapper.toEntity(dto));
    return ResponseEntity.ok(mapper.toDto(updatedTimeFactor));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    service.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
