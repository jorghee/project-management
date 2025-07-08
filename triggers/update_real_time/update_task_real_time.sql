-- Creamos la función para actualizar el tiempo real de la tarea
CREATE OR REPLACE FUNCTION update_task_real_time()
RETURNS TRIGGER AS $$
DECLARE
  v_task_id INT;
BEGIN
  -- Determinar el ID de la tarea afectada
  IF (TG_OP = 'INSERT' OR TG_OP = 'UPDATE') THEN
    SELECT at."AsiTarCod" INTO v_task_id FROM "G2T_ASIGNACION_TAREA" at WHERE at."AsiCod" = NEW."EjeAsiCod";
  ELSIF (TG_OP = 'DELETE') THEN
    SELECT at."AsiTarCod" INTO v_task_id FROM "G2T_ASIGNACION_TAREA" at WHERE at."AsiCod" = OLD."EjeAsiCod";
  END IF;

  -- Si se encontró una tarea, recalcular su tiempo real total
  IF v_task_id IS NOT NULL THEN
    UPDATE "G3M_TAREA"
    SET "TarTieReal" = (
      SELECT COALESCE(SUM(ej."EjeHor"), 0)
      FROM "G2H_EJECUCION_TAREA" ej
      JOIN "G2T_ASIGNACION_TAREA" at ON ej."EjeAsiCod" = at."AsiCod"
      WHERE at."AsiTarCod" = v_task_id
    )
    WHERE "TarCod" = v_task_id;
  END IF;

  RETURN NULL;
END;
$$ LANGUAGE plpgsql;
