-- Trigger en G1M_PROYECTO
CREATE TRIGGER trg_project_utility_update
AFTER UPDATE OF "ProMonEst", "ProDurEst", "ProDurReal" ON "G1M_PROYECTO"
FOR EACH ROW EXECUTE FUNCTION trigger_calculate_utility();

-- Trigger en G4D_COMPLEJIDAD
CREATE TRIGGER trg_complexity_utility_update
AFTER INSERT OR DELETE ON "G4D_COMPLEJIDAD"
FOR EACH ROW EXECUTE FUNCTION trigger_calculate_utility();
