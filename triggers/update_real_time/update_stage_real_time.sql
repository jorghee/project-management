-- Creamos la función para actualizar el tiempo real de la etapa
CREATE OR REPLACE FUNCTION update_stage_real_time()
RETURNS TRIGGER AS $$
DECLARE
  v_stage_id INT;
BEGIN
  -- Se activa cuando se actualiza G1M_ACTIVIDAD.
  -- Solo nos interesa si el campo 'ActTieReal' ha cambiado.
  IF NEW."ActTieReal" IS DISTINCT FROM OLD."ActTieReal" THEN
    v_stage_id := NEW."ActEtaCod";
    
    -- Recalcular el tiempo total para la etapa padre
    UPDATE "G1M_ETAPA"
    SET "EtaTieReal" = (
      SELECT COALESCE(SUM("ActTieReal"), 0)
      FROM "G1M_ACTIVIDAD"
      WHERE "ActEtaCod" = v_stage_id
    )
    WHERE "EtaCod" = v_stage_id;
  END IF;

  RETURN NULL;
END;
$$ LANGUAGE plpgsql;
