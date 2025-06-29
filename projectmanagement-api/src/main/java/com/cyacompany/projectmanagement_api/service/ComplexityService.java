package com.cyacompany.projectmanagement_api.service;

import com.cyacompany.projectmanagement_api.exception.ResourceNotFoundException;
import com.cyacompany.projectmanagement_api.model.Complexity;
import com.cyacompany.projectmanagement_api.model.ComplexityId;
import com.cyacompany.projectmanagement_api.model.ProjectUtility;
import com.cyacompany.projectmanagement_api.model.UtilityFactor;
import com.cyacompany.projectmanagement_api.repository.ComplexityRepository;
import com.cyacompany.projectmanagement_api.repository.ProjectUtilityRepository;
import com.cyacompany.projectmanagement_api.repository.UtilityFactorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ComplexityService {

  private final ComplexityRepository complexityRepository;
  private final ProjectUtilityRepository projectUtilityRepository;
  private final UtilityFactorRepository utilityFactorRepository;

  public ComplexityService(ComplexityRepository complexityRepository, ProjectUtilityRepository projectUtilityRepository,
      UtilityFactorRepository utilityFactorRepository) {
    this.complexityRepository = complexityRepository;
    this.projectUtilityRepository = projectUtilityRepository;
    this.utilityFactorRepository = utilityFactorRepository;
  }

  public List<Complexity> getAll() {
    return complexityRepository.findAll();
  }

  public Complexity getById(Integer projectUtilityId, Integer utilityFactorId) {
    ComplexityId id = new ComplexityId(projectUtilityId, utilityFactorId);
    return complexityRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Complexity not found with id: " + id));
  }

  @Transactional
  public Complexity create(Complexity complexityDetails, Integer projectUtilityId, Integer utilityFactorId) {
    ProjectUtility projectUtility = projectUtilityRepository.findById(projectUtilityId)
      .orElseThrow(() -> new ResourceNotFoundException("ProjectUtility not found with id: " + projectUtilityId));
    UtilityFactor utilityFactor = utilityFactorRepository.findById(utilityFactorId)
      .orElseThrow(() -> new ResourceNotFoundException("UtilityFactor not found with id: " + utilityFactorId));

    Complexity newComplexity = new Complexity();
    newComplexity.setProjectUtility(projectUtility);
    newComplexity.setUtilityFactor(utilityFactor);
    newComplexity.setStatus(complexityDetails.getStatus());

    return complexityRepository.save(newComplexity);
  }

  /**
  * La actualización de una entidad con clave compuesta es inherentemente compleja.
  * Normalmente no se actualiza la clave en sí. Solo se actualizan los otros campos.
  * Si la clave debe cambiar, la operación correcta es eliminar el registro antiguo y crear uno nuevo.
  */
  @Transactional
  public Complexity updateStatus(Integer projectUtilityId, Integer utilityFactorId, String newStatus) {
    Complexity existingComplexity = getById(projectUtilityId, utilityFactorId);
    existingComplexity.setStatus(newStatus);
    return complexityRepository.save(existingComplexity);
  }

  @Transactional
  public void delete(Integer projectUtilityId, Integer utilityFactorId) {
    ComplexityId id = new ComplexityId(projectUtilityId, utilityFactorId);
    if (!complexityRepository.existsById(id)) {
      throw new ResourceNotFoundException("Complexity not found with id: " + id);
    }
    complexityRepository.deleteById(id);
  }
}
