-- Crear el trigger en la tabla G2H_EJECUCION_TAREA
CREATE TRIGGER trg_update_project_cost
AFTER INSERT OR UPDATE OR DELETE ON "G2H_EJECUCION_TAREA"
FOR EACH ROW EXECUTE FUNCTION update_project_cost_on_execution();
