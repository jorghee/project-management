-- Este trigger se dispara cuando se asigna, desasigna o cambia la asignación de un empleado a una tarea.
CREATE TRIGGER trg_update_experience_factor
AFTER INSERT OR UPDATE OF asi_emp_cod, asi_tar_cod OR DELETE ON "g2t_asignacion_tarea"
FOR EACH ROW EXECUTE FUNCTION update_project_experience_factor();
