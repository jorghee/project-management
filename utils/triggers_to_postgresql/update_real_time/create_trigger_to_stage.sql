-- Creamos el trigger en la tabla G1M_ACTIVIDAD para el evento UPDATE
CREATE TRIGGER trg_update_stage_time
AFTER UPDATE ON "g1m_actividad"
FOR EACH ROW EXECUTE FUNCTION update_stage_real_time();
