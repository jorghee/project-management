-- Creamos la función principal para calcular la utilidad de un proyecto
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
  -- Obtener los datos base del proyecto
  SELECT "ProMonEst", "ProDurEst", "ProDurReal", "ProMonReal"
  INTO v_monto_estimado, v_duracion_estimada, v_duracion_real, v_costo_real
  FROM "G1M_PROYECTO"
  WHERE "ProCod" = p_project_id;

  -- Asumimos IDs en G4M_FACTOR_TIEMPO: 1=Anticipado, 2=A Tiempo, 3=Tardío
  IF v_duracion_real IS NULL OR v_duracion_estimada IS NULL THEN
    v_time_factor_id := 2; -- Default a "A Tiempo" si no hay datos
  ELSIF v_duracion_real < v_duracion_estimada THEN
    v_time_factor_id := 1; -- Anticipado
  ELSIF v_duracion_real > v_duracion_estimada THEN
    v_time_factor_id := 3; -- Tardío
  ELSE
    v_time_factor_id := 2; -- A Tiempo
  END IF;

  -- Obtenemos el valor del factor de tiempo
  SELECT "FacTieVal" INTO v_factor_tiempo_val FROM "G4M_FACTOR_TIEMPO" WHERE "FacTieCod" = v_time_factor_id;

  -- Calculamos la suma de los valores de los factores de utilidad asociados
  SELECT COALESCE(SUM(fu."FacUtiVal"), 0)
  INTO v_suma_factores_utilidad
  FROM "G4D_COMPLEJIDAD" c
  JOIN "G4M_FACTOR_UTILIDAD" fu ON c."ComFacUtiCod" = fu."FacUtiCod"
  WHERE c."ComUtiProCod" = p_project_id;

  -- Calculamos el Ingreso Ajustado
  v_ingreso_ajustado := v_monto_estimado * v_factor_tiempo_val * (1 + v_suma_factores_utilidad);

  -- Calculamos la Utilidad Neta
  v_utilidad_neta := v_ingreso_ajustado - v_costo_real;

  -- Actualizamos (o insertamos si no existe) el registro en G3D_UTILIDAD_PROYECTO
  INSERT INTO "G3D_UTILIDAD_PROYECTO" ("UtiProCod", "UtiFacTieCod", "UtiPorFin", "UtiEstReg", "UtiFacExp", "UtiPorBase")
  VALUES (p_project_id, v_time_factor_id, v_utilidad_neta, 'A', 0, 0) -- UtiFacExp y UtiPorBase podrían tener sus propias lógicas
  ON CONFLICT ("UtiProCod") DO UPDATE
  SET "UtiFacTieCod" = EXCLUDED."UtiFacTieCod",
      "UtiPorFin" = EXCLUDED."UtiPorFin";
      
END;
$$ LANGUAGE plpgsql;
