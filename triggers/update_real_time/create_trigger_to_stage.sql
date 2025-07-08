-- Creamos el trigger en la tabla G1M_ACTIVIDAD para el evento UPDATE
CREATE TRIGGER trg_update_stage_time
AFTER UPDATE ON "G1M_ACTIVIDAD"
FOR EACH ROW EXECUTE FUNCTION update_stage_real_time();
