-- Creamos la función para actualizar el tiempo real de la actividad
CREATE OR REPLACE FUNCTION update_activity_real_time()
RETURNS TRIGGER AS $$
DECLARE
  v_activity_id INT;
BEGIN
  -- El trigger se activa cuando se actualiza G3M_TAREA.
  -- Solo nos interesa si el campo 'TarTieReal' ha cambiado.
  IF NEW."TarTieReal" IS DISTINCT FROM OLD."TarTieReal" THEN
    v_activity_id := NEW."TarActCod";
    
    -- Recalcular el tiempo total para la actividad padre
    UPDATE "G1M_ACTIVIDAD"
    SET "ActTieReal" = (
      SELECT COALESCE(SUM("TarTieReal"), 0)
      FROM "G3M_TAREA"
      WHERE "TarActCod" = v_activity_id
    )
    WHERE "ActCod" = v_activity_id;
  END IF;

  RETURN NULL;
END;
$$ LANGUAGE plpgsql;
