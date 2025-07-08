CREATE OR REPLACE FUNCTION update_task_real_time()
RETURNS TRIGGER AS $$
DECLARE
  v_task_id INT;
BEGIN
  IF (TG_OP = 'INSERT' OR TG_OP = 'UPDATE') THEN
    SELECT at.asi_tar_cod INTO v_task_id FROM g2t_asignacion_tarea at WHERE at.asi_cod = NEW.eje_asi_cod;
  ELSIF (TG_OP = 'DELETE') THEN
    SELECT at.asi_tar_cod INTO v_task_id FROM g2t_asignacion_tarea at WHERE at.asi_cod = OLD.eje_asi_cod;
  END IF;

  IF v_task_id IS NOT NULL THEN
    UPDATE g3m_tarea
    SET tar_tie_real = (
      SELECT COALESCE(SUM(ej.eje_hor), 0)
      FROM g2h_ejecucion_tarea ej
      JOIN g2t_asignacion_tarea at ON ej.eje_asi_cod = at.asi_cod
      WHERE at.asi_tar_cod = v_task_id
    )
    WHERE tar_cod = v_task_id;
  END IF;

  RETURN NULL;
END;
$$ LANGUAGE plpgsql;
