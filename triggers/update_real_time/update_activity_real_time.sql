CREATE OR REPLACE FUNCTION update_activity_real_time()
RETURNS TRIGGER AS $$
DECLARE
  v_activity_id INT;
BEGIN
  IF NEW.tar_tie_real IS DISTINCT FROM OLD.tar_tie_real THEN
    v_activity_id := NEW.tar_act_cod;
    
    UPDATE g1m_actividad
    SET act_tie_real = (
      SELECT COALESCE(SUM(tar_tie_real), 0)
      FROM g3m_tarea
      WHERE tar_act_cod = v_activity_id
    )
    WHERE act_cod = v_activity_id;
  END IF;

  RETURN NULL;
END;
$$ LANGUAGE plpgsql;
