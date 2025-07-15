CREATE OR REPLACE FUNCTION update_project_experience_factor()
RETURNS TRIGGER AS $$
DECLARE
  v_project_id INT;
  v_avg_experience_factor NUMERIC(4, 2);
BEGIN
  -- Determinar el ID del proyecto afectado. La lógica busca hacia arriba desde la tarea asignada.
  IF (TG_OP = 'INSERT' OR TG_OP = 'UPDATE') THEN
    SELECT e.eta_pro_cod
    INTO v_project_id
    FROM g3m_tarea t
    JOIN g1m_actividad a ON t.tar_act_cod = a.act_cod
    JOIN g1m_etapa e ON a.act_eta_cod = e.eta_cod
    WHERE t.tar_cod = NEW.asi_tar_cod;
  ELSIF (TG_OP = 'DELETE') THEN
    SELECT e.eta_pro_cod
    INTO v_project_id
    FROM g3m_tarea t
    JOIN g1m_actividad a ON t.tar_act_cod = a.act_cod
    JOIN g1m_etapa e ON a.act_eta_cod = e.eta_cod
    WHERE t.tar_cod = OLD.asi_tar_cod;
  END IF;

  IF v_project_id IS NOT NULL THEN
    -- Calcular el promedio del valor del nivel de experiencia de todos los empleados únicos en el proyecto.
    SELECT COALESCE(AVG(el.exp_val), 0)
    INTO v_avg_experience_factor
    FROM (
      -- Subconsulta para obtener empleados únicos asignados a este proyecto
      SELECT DISTINCT at.asi_emp_cod
      FROM g2t_asignacion_tarea at
      JOIN g3m_tarea t ON at.asi_tar_cod = t.tar_cod
      JOIN g1m_actividad a ON t.tar_act_cod = a.act_cod
      JOIN g1m_etapa e ON a.act_eta_cod = e.eta_cod
      WHERE e.eta_pro_cod = v_project_id
    ) AS unique_employees
    JOIN g2m_empleado emp ON unique_employees.asi_emp_cod = emp.emp_cod
    JOIN g2m_nivel_experiencia el ON emp.emp_niv_exp_cod = el.exp_cod;
    
    -- La cláusula ON CONFLICT es para evitar errores si el registro de utilidad aún no existe.
    UPDATE g3d_utilidad_proyecto
    SET uti_fac_exp = v_avg_experience_factor
    WHERE uti_pro_cod = v_project_id;
  END IF;

  RETURN NULL; -- El resultado es ignorado para triggers AFTER
END;
$$ LANGUAGE plpgsql;
