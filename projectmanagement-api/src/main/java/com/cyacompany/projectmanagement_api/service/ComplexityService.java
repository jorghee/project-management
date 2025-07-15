package com.cyacompany.projectmanagement_api.service;

import com.cyacompany.projectmanagement_api.exception.BusinessLogicException;
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

  public ComplexityService(
      ComplexityRepository complexityRepository, 
      ProjectUtilityRepository projectUtilityRepository, 
      UtilityFactorRepository utilityFactorRepository) {
    this.complexityRepository = complexityRepository;
    this.projectUtilityRepository = projectUtilityRepository;
    this.utilityFactorRepository = utilityFactorRepository;
  }

  /**
   * Obtiene todas las relaciones de complejidad.
   * @return Lista de todas las complejidades.
   */
  public List<Complexity> getAll() {
    return complexityRepository.findAll();
  }
  
  /**
   * Obtiene una relación de complejidad específica por su clave compuesta.
   * @param projectUtilityId ID del ProjectUtility.
   * @param utilityFactorId ID del UtilityFactor.
   * @return La entidad Complexity.
   */
  public Complexity getById(Integer projectUtilityId, Integer utilityFactorId) {
    ComplexityId id = new ComplexityId(projectUtilityId, utilityFactorId);
    return complexityRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Complexity not found for ProjectUtility ID: " + 
            projectUtilityId + " and UtilityFactor ID: " + utilityFactorId));
  }

  /**
   * Obtiene todas las complejidades para un proyecto específico.
   * @param projectId ID del proyecto.
   * @return Lista de complejidades.
   */
  public List<Complexity> getByProjectId(Integer projectId) {
    return complexityRepository.findByProjectUtility_ProjectId(projectId);
  }

  /**
   * Obtiene todas las complejidades asociadas a un factor de utilidad.
   * @param factorId ID del factor de utilidad.
   * @return Lista de complejidades.
   */
  public List<Complexity> getByFactorId(Integer factorId) {
    return complexityRepository.findByUtilityFactor_Id(factorId);
  }

  /**
   * Crea una nueva relación de complejidad.
   * Valida que ambas entidades padre existan y que la relación no exista previamente.
   */
  @Transactional
  public Complexity create(Complexity complexityDetails, Integer projectUtilityId, Integer utilityFactorId) {
    ComplexityId id = new ComplexityId(projectUtilityId, utilityFactorId);
    if (complexityRepository.existsById(id)) {
      throw new BusinessLogicException("This complexity factor is already assigned to the project.");
    }

    ProjectUtility projectUtility = projectUtilityRepository.findById(projectUtilityId)
      .orElseThrow(() -> new ResourceNotFoundException("ProjectUtility not found with id: " + projectUtilityId));
    UtilityFactor utilityFactor = utilityFactorRepository.findById(utilityFactorId)
      .orElseThrow(() -> new ResourceNotFoundException("UtilityFactor not found with id: " + utilityFactorId));

    complexityDetails.setProjectUtility(projectUtility);
    complexityDetails.setUtilityFactor(utilityFactor);
    
    return complexityRepository.save(complexityDetails);
  }

  /**
   * Elimina una relación de complejidad por su clave compuesta.
   */
  @Transactional
  public void delete(Integer projectUtilityId, Integer utilityFactorId) {
    ComplexityId id = new ComplexityId(projectUtilityId, utilityFactorId);
    if (!complexityRepository.existsById(id)) {
      throw new ResourceNotFoundException("Complexity not found for ProjectUtility ID: " +
          projectUtilityId + " and UtilityFactor ID: " + utilityFactorId);
    }
    complexityRepository.deleteById(id);
  }
}
