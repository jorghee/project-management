package com.cyacompany.projectmanagement_api.controller;

import com.cyacompany.projectmanagement_api.dto.ActivityRequest;
import com.cyacompany.projectmanagement_api.dto.ActivityResponse;
import com.cyacompany.projectmanagement_api.mapper.ActivityMapper;
import com.cyacompany.projectmanagement_api.model.Activity;
import com.cyacompany.projectmanagement_api.service.ActivityService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

  private final ActivityService service;
  private final ActivityMapper mapper;

  public ActivityController(ActivityService service, ActivityMapper mapper) {
    this.service = service;
    this.mapper = mapper;
  }
  
  // No es común tener un getAll sin filtro, pero se incluye por completitud.
  // En un caso real, se filtraría por etapa o proyecto.
  @GetMapping
  public ResponseEntity<List<ActivityResponse>> getAll() {
    List<Activity> activities = service.getAll();
    List<ActivityResponse> dtoList = activities.stream()
      .map(mapper::toResponse)
      .collect(Collectors.toList());
    return ResponseEntity.ok(dtoList);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ActivityResponse> getById(@PathVariable Integer id) {
    Activity activity = service.getById(id);
    return ResponseEntity.ok(mapper.toResponse(activity));
  }

  @PostMapping
  public ResponseEntity<ActivityResponse> create(@Valid @RequestBody ActivityRequest requestDto) {
    Activity activityToCreate = mapper.toEntity(requestDto);
    Activity newActivity = service.create(activityToCreate, requestDto.getStageId(), requestDto.getResponsibleId());
    return new ResponseEntity<>(mapper.toResponse(newActivity), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ActivityResponse> update(@PathVariable Integer id, @Valid @RequestBody ActivityRequest requestDto) {
    Activity activityDetails = mapper.toEntity(requestDto);
    Activity updatedActivity = service.update(id, activityDetails, requestDto.getStageId(), requestDto.getResponsibleId());
    return ResponseEntity.ok(mapper.toResponse(updatedActivity));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
