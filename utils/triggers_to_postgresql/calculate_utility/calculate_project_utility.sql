CREATE OR REPLACE FUNCTION calculate_project_utility(p_project_id INT)
RETURNS VOID AS $$
DECLARE
  v_base_percentage NUMERIC(5, 2);
  v_time_factor_value NUMERIC(4, 2);
  v_complexity_sum NUMERIC(5, 2);
  v_experience_factor NUMERIC(4, 2);
  v_final_percentage NUMERIC(5, 2);
BEGIN
  -- 1. Obtener todos los componentes necesarios para el cálculo desde la base de datos.
  -- Usamos COALESCE para manejar valores nulos y evitar errores.
  
  -- Obtener Porcentaje Base, Factor de Experiencia (ya calculado) y el valor del Factor de Tiempo
  SELECT 
    COALESCE(pu.uti_por_base, 0), 
    COALESCE(pu.uti_fac_exp, 0),
    COALESCE(tf.fac_tie_val, 0)
  INTO 
    v_base_percentage, 
    v_experience_factor,
    v_time_factor_value
  FROM g3d_utilidad_proyecto pu
  JOIN g4m_factor_tiempo tf ON pu.uti_fac_tie_cod = tf.fac_tie_cod
  WHERE pu.uti_pro_cod = p_project_id;

  -- Si no se encuentra una utilidad para el proyecto, salimos.
  IF NOT FOUND THEN
    RETURN;
  END IF;

  -- Obtener la suma de los Factores de Utilidad (Complejidad)
  SELECT COALESCE(SUM(uf.fac_uti_val), 0)
  INTO v_complexity_sum
  FROM g4d_complejidad c
  JOIN g4m_factor_utilidad uf ON c.com_fac_uti_cod = uf.fac_uti_cod
  WHERE c.com_uti_pro_cod = p_project_id;
  
  -- 2. Aplicar la fórmula
  v_final_percentage := v_base_percentage + v_time_factor_value + v_complexity_sum + v_experience_factor;

  -- 3. Actualizar el campo de porcentaje final en la tabla de utilidad.
  UPDATE g3d_utilidad_proyecto
  SET uti_por_fin = v_final_percentage
  WHERE uti_pro_cod = p_project_id;

END;
$$ LANGUAGE plpgsql;
