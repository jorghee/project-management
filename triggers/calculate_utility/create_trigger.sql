CREATE TRIGGER trg_project_utility_update
AFTER UPDATE OF pro_mon_est, pro_dur_est, pro_dur_real ON g1m_proyecto
FOR EACH ROW EXECUTE FUNCTION trigger_calculate_utility();

CREATE TRIGGER trg_complexity_utility_update
AFTER INSERT OR DELETE ON g4d_complejidad
FOR EACH ROW EXECUTE FUNCTION trigger_calculate_utility();
