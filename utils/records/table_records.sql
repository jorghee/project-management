-- POBLAMIENTO DE DATOS PARA PROJECT MANAGEMENT API (MySQL)

SET autocommit=0;
START TRANSACTION;

SET FOREIGN_KEY_CHECKS=0;

DELETE FROM g2h_ejecucion_tarea;
DELETE FROM g2t_asignacion_tarea;
DELETE FROM g3m_tarea;
DELETE FROM g1m_actividad;
DELETE FROM g1m_etapa;
DELETE FROM g4d_complejidad;
DELETE FROM g3d_utilidad_proyecto;
DELETE FROM g1m_proyecto;
DELETE FROM g2c_disponibilidad;
DELETE FROM g2m_empleado;
DELETE FROM g1m_cliente;
DELETE FROM g1z_tipo_cliente;
DELETE FROM g1z_estado_proyecto;
DELETE FROM g2m_cargo;
DELETE FROM g2m_nivel_experiencia;
DELETE FROM g3z_prioridad;
DELETE FROM g3z_tipo_tarea;
DELETE FROM g4m_factor_tiempo;
DELETE FROM g4m_factor_utilidad;

SET FOREIGN_KEY_CHECKS=1;

-- ---------------------------------------------------------------------------------
-- Poblamiento de catálogos
-- ---------------------------------------------------------------------------------

-- Tipos de Cliente
INSERT INTO `g1z_tipo_cliente` (`tip_cli_cod`, `tip_cli_des`, `tip_cli_est_reg`) VALUES
(1, 'Banca Privada', 'A'),
(2, 'Entidad Pública', 'A'),
(3, 'Servicios Públicos', 'A');

-- Estados de Proyecto
INSERT INTO `g1z_estado_proyecto` (`est_pro_cod`, `est_pro_des`, `est_pro_est_reg`) VALUES
(1, 'Definición', 'A'),
(2, 'En Ejecución', 'A'),
(3, 'Completado', 'A'),
(4, 'Pausado', 'A');

-- Cargos de Empleados
INSERT INTO `g2m_cargo` (`car_cod`, `car_des`, `car_cos_hor`, `car_est_reg`) VALUES
(1, 'Jefe de Proyecto', 70.00, 'A'),
(2, 'Analista Funcional', 55.00, 'A'),
(3, 'Desarrollador Senior', 65.00, 'A'),
(4, 'Desarrollador Junior', 40.00, 'A'),
(5, 'Analista QA', 50.00, 'A'),
(6, 'Administrador de BD', 60.00, 'A');

-- Niveles de Experiencia
INSERT INTO `g2m_nivel_experiencia` (`exp_cod`, `exp_des`, `exp_val`, `exp_est_reg`) VALUES
(1, 'Junior', 1.00, 'A'),
(2, 'Semi-Senior', 1.50, 'A'),
(3, 'Senior', 2.00, 'A'),
(4, 'Líder Técnico', 2.50, 'A');

-- Prioridades de Tarea
INSERT INTO `g3z_prioridad` (`pri_cod`, `pri_des`, `pri_est_reg`) VALUES
(1, 'Baja', 'A'),
(2, 'Media', 'A'),
(3, 'Alta', 'A'),
(4, 'Crítica', 'A');

-- Tipos de Tarea
INSERT INTO `g3z_tipo_tarea` (`tip_tar_cod`, `tip_tar_des`, `est_reg`) VALUES
(1, 'Análisis', 'A'),
(2, 'Desarrollo Backend', 'A'),
(3, 'Desarrollo Frontend', 'A'),
(4, 'Pruebas', 'A'),
(5, 'Despliegue', 'A');

-- Factores de Tiempo
INSERT INTO `g4m_factor_tiempo` (`fac_tie_cod`, `fac_tie_des`, `fac_tie_val`, `fac_est_reg`) VALUES
(1, 'Normal', 2.00, 'A'),
(2, 'Urgente', 5.00, 'A'),
(3, 'Plazo Extendido', 0.50, 'A');

-- Factores de Utilidad (Complejidad)
INSERT INTO `g4m_factor_utilidad` (`fac_uti_cod`, `fac_uti_des`, `fac_uti_val`, `fac_est_reg`) VALUES
(1, 'Alta Exposición al Cliente', 3.00, 'A'),
(2, 'Innovación Tecnológica', 2.50, 'A'),
(3, 'Riesgo Operativo Alto', 4.00, 'A'),
(4, 'Integración con Sistemas Legados', 3.50, 'A');

-- ---------------------------------------------------------------------------------
-- Poblamiento de entidades principales
-- ---------------------------------------------------------------------------------

-- Clientes
INSERT INTO `g1m_cliente` (`cli_cod`, `cli_tip_cli_cod`, `cli_nom`, `cli_fec_ing`, `cli_fec_cse`, `cli_est`, `cli_est_reg`) VALUES
(101, 1, 'Banco de Crédito del Perú', '2022-01-15', NULL, 'A', 'A'),
(102, 1, 'Interbank', '2021-11-20', NULL, 'A', 'A'),
(103, 2, 'RENIEC - Sede Arequipa', '2023-03-10', NULL, 'A', 'A'),
(104, 3, 'Sociedad Eléctrica del Sur Oeste', '2020-05-01', NULL, 'A', 'A');

-- Empleados
INSERT INTO `g2m_empleado` (`emp_cod`, `emp_car_cod`, `emp_niv_exp_cod`, `emp_nom`, `emp_est_reg`) VALUES
(501, 1, 4, 'Alexandra Raquel Quispe Arratea', 'A'),
(502, 3, 3, 'Paul Andre Cari Lipe', 'A'),
(503, 4, 1, 'Jessica Milagros Belon Corrales', 'A'),
(504, 5, 2, 'Jorge Luis Mamani Huarsaya', 'A');

-- Disponibilidad
INSERT INTO `g2c_disponibilidad` (`dis_emp_cod`, `dis_est`, `dis_cap_sem`, `dis_car_act`, `dis_est_reg`) VALUES
(501, 'D', 48, 0, 'A'),
(502, 'D', 48, 0, 'A'),
(503, 'D', 48, 0, 'A'),
(504, 'D', 48, 0, 'A');

-- ---------------------------------------------------------------------------------
-- Crear un proyecto como prueba
-- ---------------------------------------------------------------------------------

INSERT INTO `g1m_proyecto` (`pro_cod`, `pro_cli_cod`, `pro_est_pro_cod`, `pro_nom`, `pro_fec_ini`, `pro_fec_fin`, `pro_mon_est`, `pro_mon_real`, `pro_est_reg`) VALUES
(201, 101, 2, 'Implementación de Yape en Comercios de Arequipa', '2024-08-01', NULL, 50000.00, 0.00, 'A');

INSERT INTO `g3d_utilidad_proyecto` (`uti_pro_cod`, `uti_fac_tie_cod`, `uti_fac_exp`, `uti_por_base`, `uti_por_fin`, `uti_est_reg`) VALUES
(201, 2, 0.00, 10.00, 0.00, 'A');

INSERT INTO `g4d_complejidad` (`com_uti_pro_cod`, `com_fac_uti_cod`, `com_est_reg`) VALUES
(201, 1, 'A'),
(201, 4, 'A');

INSERT INTO `g1m_etapa` (`eta_cod`, `eta_pro_cod`, `eta_nom`, `eta_tie_est`, `eta_tie_real`, `eta_est_reg`) VALUES
(301, 201, 'Análisis y Diseño', 80, 0, 'A'),
(302, 201, 'Desarrollo e Integración', 200, 0, 'A');

INSERT INTO `g1m_actividad` (`act_cod`, `act_eta_cod`, `act_emp_cod`, `act_nom`, `act_tie_est`, `act_tie_real`, `act_est_reg`) VALUES
(401, 301, 501, 'Levantamiento de Requerimientos', 40, 0, 'A');

INSERT INTO `g1m_actividad` (`act_cod`, `act_eta_cod`, `act_emp_cod`, `act_nom`, `act_tie_est`, `act_tie_real`, `act_est_reg`) VALUES
(402, 302, 502, 'Desarrollo de Microservicio de Pagos', 120, 0, 'A');

INSERT INTO `g3m_tarea` (`tar_cod`, `tar_act_cod`, `tar_tip_tar_cod`, `tar_pri_cod`, `tar_des`, `tar_tie_est`, `tar_tie_real`, `tar_est`, `tar_est_reg`) VALUES
(601, 402, 2, 3, 'Crear endpoint para procesar pago Yape', 16, 0, 'P', 'A'),
(602, 402, 4, 2, 'Crear pruebas unitarias para el servicio de pago', 8, 0, 'P', 'A');

-- ---------------------------------------------------------------------------------
-- Activación de triggers
-- ---------------------------------------------------------------------------------

-- >> TRIGGER 1. Asignación de Tareas <<
-- Esto disparará `trg_update_experience_on_assignment`.
-- Deberá llamar a `sp_update_project_experience_factor` para calcular el promedio de experiencia.
-- El promedio (Senior 2.00 + Junior 1.00)/2 = 1.50 debe actualizar `g3d_utilidad_proyecto.uti_fac_exp`.
-- El cambio en `uti_fac_exp` a su vez disparará `trg_recalculate_on_utility_change`,
-- que llamará a `sp_calculate_project_utility` para recalcular `uti_por_fin`.
-- Cálculo esperado: Base(10) + Tiempo(5) + Complejidad(3+3.5) + Experiencia(1.5) = 23.00

-- INSERT INTO `g2t_asignacion_tarea` (`AsiCod`, `AsiEmpCod`, `AsiTarCod`, `AsiEstReg`) VALUES
-- (701, 502, 601, 'A'),
-- (702, 503, 601, 'A');


-- >> TRIGGER 2. Registro de Horas <<
-- Cada uno de estos INSERTs disparará `trg_update_task_time` y `trg_update_project_cost`.
-- El tiempo se propagará en cascada: Tarea -> Actividad -> Etapa.
-- El costo se sumará en `g1m_proyecto.pro_mon_real`.

-- Carlos (Senior, S/65 por hora) trabaja 8 horas. Costo = 8 * 65 = 520
-- INSERT INTO `g2h_ejecucion_tarea` (`EjeCod`, `EjeAsiCod`, `EjeFec`, `EjeHor`, `EjeEstReg`) VALUES
-- (801, 701, '2024-08-05', 8, 'A');

-- Lucia (Junior, S/40 por hora) trabaja 6 horas. Costo = 6 * 40 = 240
-- INSERT INTO `g2h_ejecucion_tarea` (`EjeCod`, `EjeAsiCod`, `EjeFec`, `EjeHor`, `EjeEstReg`) VALUES
-- (802, 702, '2024-08-05', 6, 'A');


COMMIT;
