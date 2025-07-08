CREATE OR REPLACE FUNCTION trigger_calculate_utility()
RETURNS TRIGGER AS $$
DECLARE
  v_project_id INT;
BEGIN
  IF (TG_OP = 'DELETE') THEN
    v_project_id := OLD.pro_cod;
  ELSE
    v_project_id := NEW.pro_cod;
  END IF;
  
  IF TG_TABLE_NAME = 'g4d_complejidad' THEN
    IF (TG_OP = 'DELETE') THEN
      v_project_id := OLD.com_uti_pro_cod;
    ELSE
      v_project_id := NEW.com_uti_pro_cod;
    END IF;
  END IF;

  PERFORM calculate_project_utility(v_project_id);
  RETURN NULL;
END;
$$ LANGUAGE plpgsql;
