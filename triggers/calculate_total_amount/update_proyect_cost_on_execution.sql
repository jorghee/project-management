CREATE OR REPLACE FUNCTION update_project_cost_on_execution()
RETURNS TRIGGER AS $$
DECLARE
  v_project_id INT;
  v_total_cost NUMERIC(9, 2);
BEGIN
  -- Determinar el ID del proyecto afectado por el cambio
  -- Si es un INSERT o UPDATE, usamos los nuevos datos (NEW)
  IF (TG_OP = 'INSERT' OR TG_OP = 'UPDATE') THEN
    SELECT p."ProCod"
    INTO v_project_id
    FROM "G1M_PROYECTO" p
    JOIN "G1M_ETAPA" e ON p."ProCod" = e."EtaProCod"
    JOIN "G1M_ACTIVIDAD" a ON e."EtaCod" = a."ActEtaCod"
    JOIN "G3M_TAREA" t ON a."ActCod" = t."TarActCod"
    JOIN "G2T_ASIGNACION_TAREA" at ON t."TarCod" = at."AsiTarCod"
    WHERE at."AsiCod" = NEW."EjeAsiCod";
  -- Si es un DELETE, usamos los datos antiguos (OLD)
  ELSIF (TG_OP = 'DELETE') THEN
      SELECT p."ProCod"
      INTO v_project_id
      FROM "G1M_PROYECTO" p
      JOIN "G1M_ETAPA" e ON p."ProCod" = e."EtaProCod"
      JOIN "G1M_ACTIVIDAD" a ON e."EtaCod" = a."ActEtaCod"
      JOIN "G3M_TAREA" t ON a."ActCod" = t."TarActCod"
      JOIN "G2T_ASIGNACION_TAREA" at ON t."TarCod" = at."AsiTarCod"
      WHERE at."AsiCod" = OLD."EjeAsiCod";
  END IF;

  -- Si se encontró un proyecto, recalcular su costo total
  IF v_project_id IS NOT NULL THEN
    -- Calculamos la suma total de costos para ese proyecto
    SELECT COALESCE(SUM(ej."EjeHor" * cr."CarCosHor"), 0)
    INTO v_total_cost
    FROM "G2H_EJECUCION_TAREA" ej
    JOIN "G2T_ASIGNACION_TAREA" at ON ej."EjeAsiCod" = at."AsiCod"
    JOIN "G2M_EMPLEADO" em ON at."AsiEmpCod" = em."EmpCod"
    JOIN "G2M_CARGO" cr ON em."EmpCarCod" = cr."CarCod"
    JOIN "G3M_TAREA" t ON at."AsiTarCod" = t."TarCod"
    JOIN "G1M_ACTIVIDAD" a ON t."TarActCod" = a."ActCod"
    JOIN "G1M_ETAPA" e ON a."ActEtaCod" = e."EtaCod"
    WHERE e."EtaProCod" = v_project_id;
    
    -- Actualizamos el campo ProMonReal en la tabla G1M_PROYECTO
    UPDATE "G1M_PROYECTO"
    SET "ProMonReal" = v_total_cost
    WHERE "ProCod" = v_project_id;
  END IF;

  -- El valor de retorno del trigger es ignorado para triggers AFTER
  RETURN NULL;
END;
$$ LANGUAGE plpgsql;
