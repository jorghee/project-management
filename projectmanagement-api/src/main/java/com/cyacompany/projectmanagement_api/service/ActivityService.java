package com.cyacompany.projectmanagement_api.service;

import com.cyacompany.projectmanagement_api.exception.BusinessLogicException;
import com.cyacompany.projectmanagement_api.exception.ResourceNotFoundException;
import com.cyacompany.projectmanagement_api.model.Activity;
import com.cyacompany.projectmanagement_api.model.Employee;
import com.cyacompany.projectmanagement_api.model.Stage;
import com.cyacompany.projectmanagement_api.repository.ActivityRepository;
import com.cyacompany.projectmanagement_api.repository.EmployeeRepository;
import com.cyacompany.projectmanagement_api.repository.StageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ActivityService {

  private final ActivityRepository activityRepository;
  private final StageRepository stageRepository;
  private final EmployeeRepository employeeRepository;

  public ActivityService(ActivityRepository activityRepository, StageRepository stageRepository, EmployeeRepository employeeRepository) {
    this.activityRepository = activityRepository;
    this.stageRepository = stageRepository;
    this.employeeRepository = employeeRepository;
  }

  public List<Activity> getAll() {
    return activityRepository.findAll();
  }

  public Activity getById(Integer id) {
    return activityRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Activity not found with id: " + id));
  }

  @Transactional
  public Activity create(Activity activity, Integer stageId, Integer responsibleId) {
    if (activityRepository.existsById(activity.getId())) {
      throw new BusinessLogicException("Cannot create Activity. ID " + activity.getId() + " already exists.");
    }

    Stage stage = stageRepository.findById(stageId)
      .orElseThrow(() -> new ResourceNotFoundException("Stage not found with id: " + stageId));
    Employee responsible = employeeRepository.findById(responsibleId)
      .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + responsibleId));

    activity.setStage(stage);
    activity.setResponsible(responsible);
    return activityRepository.save(activity);
  }

  @Transactional
  public Activity update(Integer id, Activity activityDetails, Integer stageId, Integer responsibleId) {
    if (!activityRepository.existsById(id)) {
      throw new ResourceNotFoundException("Activity not found with id: " + id);
    }

    Stage stage = stageRepository.findById(stageId)
      .orElseThrow(() -> new ResourceNotFoundException("Stage not found with id: " + stageId));
    Employee responsible = employeeRepository.findById(responsibleId)
      .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + responsibleId));

    activityDetails.setId(id);
    activityDetails.setStage(stage);
    activityDetails.setResponsible(responsible);
   
    return activityRepository.save(activityDetails);
  }

  @Transactional
  public void delete(Integer id) {
    if (!activityRepository.existsById(id)) {
      throw new ResourceNotFoundException("Activity not found with id: " + id);
    }
    activityRepository.deleteById(id);
  }
}
