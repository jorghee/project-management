CREATE OR REPLACE FUNCTION update_project_cost_on_execution()
RETURNS TRIGGER AS $$
DECLARE
  v_project_id INT;
  v_task_assignment_id INT;
  v_total_cost NUMERIC(9, 2);
BEGIN
  -- Determinar el ID de la asignación y del proyecto afectado
  IF (TG_OP = 'INSERT' OR TG_OP = 'UPDATE') THEN
    v_task_assignment_id := NEW.eje_asi_cod;
  ELSIF (TG_OP = 'DELETE') THEN
    v_task_assignment_id := OLD.eje_asi_cod;
  END IF;

  SELECT p.pro_cod
  INTO v_project_id
  FROM g1m_proyecto p
  JOIN g1m_etapa e ON p.pro_cod = e.eta_pro_cod
  JOIN g1m_actividad a ON e.eta_cod = a.act_eta_cod
  JOIN g3m_tarea t ON a.act_cod = t.tar_act_cod
  JOIN g2t_asignacion_tarea at ON t.tar_cod = at.asi_tar_cod
  WHERE at.asi_cod = v_task_assignment_id;

  IF v_project_id IS NOT NULL THEN
    -- Calcular la suma total de costos
    SELECT COALESCE(SUM(ej.eje_hor * cr.car_cos_hor), 0)
    INTO v_total_cost
    FROM g2h_ejecucion_tarea ej
    JOIN g2t_asignacion_tarea at ON ej.eje_asi_cod = at.asi_cod
    JOIN g2m_empleado em ON at.asi_emp_cod = em.emp_cod
    JOIN g2m_cargo cr ON em.emp_car_cod = cr.car_cod
    JOIN g3m_tarea t ON at.asi_tar_cod = t.tar_cod
    JOIN g1m_actividad a ON t.tar_act_cod = a.act_cod
    JOIN g1m_etapa e ON a.act_eta_cod = e.eta_cod
    WHERE e.eta_pro_cod = v_project_id;

    -- Actualizar el campo pro_mon_real
    UPDATE g1m_proyecto
    SET pro_mon_real = v_total_cost
    WHERE pro_cod = v_project_id;

    -- Dado que un cambio en la ejecución no altera los empleados, no es necesario
    -- llamar a update_project_experience_factor aquí.
    -- Pero sí llamamos al cálculo final de utilidad, ya que podría depender de otros
    -- factores que sí cambian. En nuestro modelo, no es estrictamente necesario,
    -- pero es una buena práctica para futuras ampliaciones 
    PERFORM calculate_project_utility(v_project_id);
  END IF;

  RETURN NULL;
END;
$$ LANGUAGE plpgsql;
