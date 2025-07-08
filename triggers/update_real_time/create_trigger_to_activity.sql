-- Creamos el trigger en la tabla G3M_TAREA para el evento UPDATE
CREATE TRIGGER trg_update_activity_time
AFTER UPDATE ON "g3m_tarea"
FOR EACH ROW EXECUTE FUNCTION update_activity_real_time();
