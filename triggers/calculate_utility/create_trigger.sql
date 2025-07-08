CREATE OR REPLACE FUNCTION trigger_calculate_utility()
RETURNS TRIGGER AS $$
DECLARE
  v_project_id INT;
BEGIN
  IF (TG_OP = 'DELETE') THEN
    v_project_id := OLD."ProCod";
  ELSE
    v_project_id := NEW."ProCod";
  END IF;
  
  -- Para la tabla de complejidad
  IF TG_TABLE_NAME = 'G4D_COMPLEJIDAD' THEN
    IF (TG_OP = 'DELETE') THEN
      v_project_id := OLD."ComUtiProCod";
    ELSE
      v_project_id := NEW."ComUtiProCod";
    END IF;
  END IF;

  -- Llamamos a la función principal de cálculo
  PERFORM calculate_project_utility(v_project_id);
  RETURN NULL;
END;
$$ LANGUAGE plpgsql;
