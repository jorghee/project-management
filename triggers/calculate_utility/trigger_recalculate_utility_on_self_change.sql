CREATE OR REPLACE FUNCTION trigger_recalculate_utility_on_self_change()
RETURNS TRIGGER AS $$
BEGIN
  -- Llama a la función principal con el ID del proyecto que está siendo modificado.
  PERFORM calculate_project_utility(NEW.uti_pro_cod);
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_recalculate_on_utility_change
AFTER INSERT OR UPDATE OF uti_por_base, uti_fac_tie_cod, uti_fac_exp ON "g3d_utilidad_proyecto"
FOR EACH ROW EXECUTE FUNCTION trigger_recalculate_utility_on_self_change();
