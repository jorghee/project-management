CREATE OR REPLACE FUNCTION calculate_project_utility(p_project_id INT)
RETURNS VOID AS $$
DECLARE
  v_monto_estimado NUMERIC;
  v_duracion_estimada INT;
  v_duracion_real INT;
  v_costo_real NUMERIC;
  v_factor_tiempo_val NUMERIC;
  v_suma_factores_utilidad NUMERIC;
  v_ingreso_ajustado NUMERIC;
  v_utilidad_neta NUMERIC;
  v_time_factor_id INT;
BEGIN
  SELECT pro_mon_est, pro_dur_est, pro_dur_real, pro_mon_real
  INTO v_monto_estimado, v_duracion_estimada, v_duracion_real, v_costo_real
  FROM g1m_proyecto
  WHERE pro_cod = p_project_id;

  IF v_duracion_real IS NULL OR v_duracion_estimada IS NULL THEN
    v_time_factor_id := 2; 
  ELSIF v_duracion_real < v_duracion_estimada THEN
    v_time_factor_id := 1;
  ELSIF v_duracion_real > v_duracion_estimada THEN
    v_time_factor_id := 3;
  ELSE
    v_time_factor_id := 2;
  END IF;

  SELECT fac_tie_val INTO v_factor_tiempo_val FROM g4m_factor_tiempo WHERE fac_tie_cod = v_time_factor_id;

  SELECT COALESCE(SUM(fu.fac_uti_val), 0)
  INTO v_suma_factores_utilidad
  FROM g4d_complejidad c
  JOIN g4m_factor_utilidad fu ON c.com_fac_uti_cod = fu.fac_uti_cod
  WHERE c.com_uti_pro_cod = p_project_id;

  v_ingreso_ajustado := v_monto_estimado * v_factor_tiempo_val * (1 + v_suma_factores_utilidad);
  v_utilidad_neta := v_ingreso_ajustado - v_costo_real;

  INSERT INTO g3d_utilidad_proyecto (uti_pro_cod, uti_fac_tie_cod, uti_por_fin, uti_est_reg, uti_fac_exp, uti_por_base)
  VALUES (p_project_id, v_time_factor_id, v_utilidad_neta, 'A', 0, 0)
  ON CONFLICT (uti_pro_cod) DO UPDATE
  SET uti_fac_tie_cod = EXCLUDED.uti_fac_tie_cod,
      uti_por_fin = EXCLUDED.uti_por_fin;
END;
$$ LANGUAGE plpgsql;
