CREATE OR REPLACE FUNCTION update_stage_real_time()
RETURNS TRIGGER AS $$
DECLARE
  v_stage_id INT;
BEGIN
  IF NEW.act_tie_real IS DISTINCT FROM OLD.act_tie_real THEN
    v_stage_id := NEW.act_eta_cod;
    
    UPDATE g1m_etapa
    SET eta_tie_real = (
      SELECT COALESCE(SUM(act_tie_real), 0)
      FROM g1m_actividad
      WHERE act_eta_cod = v_stage_id
    )
    WHERE eta_cod = v_stage_id;
  END IF;

  RETURN NULL;
END;
$$ LANGUAGE plpgsql;
