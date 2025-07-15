CREATE OR REPLACE FUNCTION trigger_recalculate_utility_on_complexity_change()
RETURNS TRIGGER AS $$
DECLARE
  v_project_id INT;
BEGIN
  IF (TG_OP = 'DELETE') THEN
    v_project_id := OLD.com_uti_pro_cod;
  ELSE
    v_project_id := NEW.com_uti_pro_cod;
  END IF;
  
  PERFORM calculate_project_utility(v_project_id);
  RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_recalculate_on_complexity_change
AFTER INSERT OR DELETE OR UPDATE ON "g4d_complejidad"
FOR EACH ROW EXECUTE FUNCTION trigger_recalculate_utility_on_complexity_change();
