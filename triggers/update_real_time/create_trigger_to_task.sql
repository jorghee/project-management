-- Creamos el trigger en la tabla G2H_EJECUCION_TAREA
CREATE TRIGGER trg_update_task_time
AFTER INSERT OR UPDATE OR DELETE ON "g2h_ejecucion_tarea"
FOR EACH ROW EXECUTE FUNCTION update_task_real_time();
