# Documentación de la API de Gestión de Proyectos

## Gestión de Tipos de Cliente (`/api/client-types`)

Recurso para administrar los tipos de cliente (ej. Corporativo, Gubernamental).

### Obtener todos los Tipos de Cliente

-   **Método HTTP:** `GET`
-   **Ruta:** `/api/client-types`
-   **Descripción:** Recupera una lista de todos los tipos de cliente.
-   **Ejemplo de Respuesta (200 OK):**
    ```json
    [
      {
        "id": 1,
        "description": "Corporativo",
        "status": "A"
      },
      {
        "id": 2,
        "description": "Gubernamental",
        "status": "A"
      }
    ]
    ```

### Obtener un Tipo de Cliente por ID

-   **Método HTTP:** `GET`
-   **Ruta:** `/api/client-types/{id}`
-   **Descripción:** Recupera un tipo de cliente específico por su ID.
-   **Parámetros de Ruta:**
    -   `id` (Integer): ID único del tipo de cliente.
-   **Ejemplo de Respuesta (200 OK):**
    ```json
    {
      "id": 1,
      "description": "Corporativo",
      "status": "A"
    }
    ```

### Crear un nuevo Tipo de Cliente

-   **Método HTTP:** `POST`
-   **Ruta:** `/api/client-types`
-   **Descripción:** Crea un nuevo tipo de cliente.
-   **Cuerpo de la Solicitud (application/json):**
    ```json
    {
      "description": "Pequeña Empresa",
      "status": "A"
    }
    ```
-   **Ejemplo de Respuesta (201 Created):**
    ```json
    {
      "id": 3,
      "description": "Pequeña Empresa",
      "status": "A"
    }
    ```

### Actualizar un Tipo de Cliente

-   **Método HTTP:** `PUT`
-   **Ruta:** `/api/client-types/{id}`
-   **Descripción:** Actualiza la información de un tipo de cliente existente.
-   **Parámetros de Ruta:**
    -   `id` (Integer): ID único del tipo de cliente a actualizar.
-   **Cuerpo de la Solicitud (application/json):**
    ```json
    {
      "description": "Pequeña y Mediana Empresa",
      "status": "I"
    }
    ```
-   **Ejemplo de Respuesta (200 OK):**
    ```json
    {
      "id": 3,
      "description": "Pequeña y Mediana Empresa",
      "status": "I"
    }
    ```

### Eliminar un Tipo de Cliente

-   **Método HTTP:** `DELETE`
-   **Ruta:** `/api/client-types/{id}`
-   **Descripción:** Elimina un tipo de cliente por su ID.
-   **Parámetros de Ruta:**
    -   `id` (Integer): ID único del tipo de cliente a eliminar.
-   **Respuesta Exitosa:** `204 No Content`

---

## Gestión de Cargos (`/api/positions`)

Recurso para administrar los cargos de los empleados (ej. Desarrollador, Analista).

### Obtener todos los Cargos

-   **Método HTTP:** `GET`
-   **Ruta:** `/api/positions`
-   **Descripción:** Recupera una lista de todos los cargos.
-   **Ejemplo de Respuesta (200 OK):**
    ```json
    [
      {
        "id": 10,
        "description": "Desarrollador Backend",
        "status": "A"
      },
      {
        "id": 11,
        "description": "Jefe de Proyecto",
        "status": "A"
      }
    ]
    ```

### Obtener un Cargo por ID

-   **Método HTTP:** `GET`
-   **Ruta:** `/api/positions/{id}`
-   **Descripción:** Recupera un cargo específico por su ID.
-   **Parámetros de Ruta:**
    -   `id` (Integer): ID único del cargo.
-   **Ejemplo de Respuesta (200 OK):**
    ```json
    {
      "id": 10,
      "description": "Desarrollador Backend",
      "status": "A"
    }
    ```

### Crear un nuevo Cargo

-   **Método HTTP:** `POST`
-   **Ruta:** `/api/positions`
-   **Descripción:** Crea un nuevo cargo.
-   **Cuerpo de la Solicitud (application/json):**
    ```json
    {
      "description": "Analista QA",
      "status": "A"
    }
    ```
-   **Ejemplo de Respuesta (201 Created):**
    ```json
    {
      "id": 12,
      "description": "Analista QA",
      "status": "A"
    }
    ```

### Actualizar un Cargo

-   **Método HTTP:** `PUT`
-   **Ruta:** `/api/positions/{id}`
-   **Descripción:** Actualiza un cargo existente.
-   **Parámetros de Ruta:**
    -   `id` (Integer): ID único del cargo.
-   **Cuerpo de la Solicitud (application/json):**
    ```json
    {
      "description": "Analista de Calidad (QA)",
      "status": "A"
    }
    ```
-   **Ejemplo de Respuesta (200 OK):**
    ```json
    {
      "id": 12,
      "description": "Analista de Calidad (QA)",
      "status": "A"
    }
    ```

### Eliminar un Cargo

-   **Método HTTP:** `DELETE`
-   **Ruta:** `/api/positions/{id}`
-   **Descripción:** Elimina un cargo por su ID.
-   **Parámetros de Ruta:**
    -   `id` (Integer): ID único del cargo.
-   **Respuesta Exitosa:** `204 No Content`

---

## Gestión de Prioridades (`/api/priorities`)

Recurso para administrar las prioridades de las tareas (ej. Alta, Media, Baja).

### Obtener todas las Prioridades

-   **Método HTTP:** `GET`
-   **Ruta:** `/api/priorities`
-   **Descripción:** Recupera una lista de todas las prioridades.
-   **Ejemplo de Respuesta (200 OK):**
    ```json
    [
      {
        "id": 1,
        "description": "Alta",
        "status": "A"
      },
      {
        "id": 2,
        "description": "Media",
        "status": "A"
      }
    ]
    ```

### Obtener una Prioridad por ID

-   **Método HTTP:** `GET`
-   **Ruta:** `/api/priorities/{id}`
-   **Descripción:** Recupera una prioridad específica por su ID.
-   **Parámetros de Ruta:**
    -   `id` (Integer): ID único de la prioridad.
-   **Ejemplo de Respuesta (200 OK):**
    ```json
    {
      "id": 1,
      "description": "Alta",
      "status": "A"
    }
    ```

### Crear una nueva Prioridad

-   **Método HTTP:** `POST`
-   **Ruta:** `/api/priorities`
-   **Descripción:** Crea una nueva prioridad.
-   **Cuerpo de la Solicitud (application/json):**
    ```json
    {
      "description": "Baja",
      "status": "A"
    }
    ```
-   **Ejemplo de Respuesta (201 Created):**
    ```json
    {
      "id": 3,
      "description": "Baja",
      "status": "A"
    }
    ```

### Actualizar una Prioridad

-   **Método HTTP:** `PUT`
-   **Ruta:** `/api/priorities/{id}`
-   **Descripción:** Actualiza una prioridad existente.
-   **Parámetros de Ruta:**
    -   `id` (Integer): ID único de la prioridad.
-   **Cuerpo de la Solicitud (application/json):**
    ```json
    {
      "description": "Urgente",
      "status": "A"
    }
    ```
-   **Ejemplo de Respuesta (200 OK):**
    ```json
    {
      "id": 1,
      "description": "Urgente",
      "status": "A"
    }
    ```

### Eliminar una Prioridad

-   **Método HTTP:** `DELETE`
-   **Ruta:** `/api/priorities/{id}`
-   **Descripción:** Elimina una prioridad por su ID.
-   **Parámetros de Ruta:**
    -   `id` (Integer): ID único de la prioridad.
-   **Respuesta Exitosa:** `204 No Content`

---

## Gestión de Estados de Proyecto (`/api/project-status`)

Recurso para administrar los estados de los proyectos (ej. En Progreso, Finalizado).

### Obtener todos los Estados de Proyecto

-   **Método HTTP:** `GET`
-   **Ruta:** `/api/project-status`
-   **Descripción:** Recupera una lista de todos los estados de proyecto.
-   **Ejemplo de Respuesta (200 OK):**
    ```json
    [
      {
        "id": 1,
        "description": "Planificado",
        "status": "A"
      },
      {
        "id": 2,
        "description": "En Ejecución",
        "status": "A"
      }
    ]
    ```

### Obtener un Estado de Proyecto por ID

-   **Método HTTP:** `GET`
-   **Ruta:** `/api/project-status/{id}`
-   **Descripción:** Recupera un estado de proyecto específico por su ID.
-   **Parámetros de Ruta:**
    -   `id` (Integer): ID único del estado.
-   **Ejemplo de Respuesta (200 OK):**
    ```json
    {
      "id": 2,
      "description": "En Ejecución",
      "status": "A"
    }
    ```

### Crear un nuevo Estado de Proyecto

-   **Método HTTP:** `POST`
-   **Ruta:** `/api/project-status`
-   **Descripción:** Crea un nuevo estado de proyecto.
-   **Cuerpo de la Solicitud (application/json):**
    ```json
    {
      "description": "Finalizado",
      "status": "A"
    }
    ```
-   **Ejemplo de Respuesta (201 Created):**
    ```json
    {
      "id": 3,
      "description": "Finalizado",
      "status": "A"
    }
    ```

### Actualizar un Estado de Proyecto

-   **Método HTTP:** `PUT`
-   **Ruta:** `/api/project-status/{id}`
-   **Descripción:** Actualiza un estado de proyecto existente.
-   **Parámetros de Ruta:**
    -   `id` (Integer): ID único del estado.
-   **Cuerpo de la Solicitud (application/json):**
    ```json
    {
      "description": "En Progreso",
      "status": "A"
    }
    ```
-   **Ejemplo de Respuesta (200 OK):**
    ```json
    {
      "id": 2,
      "description": "En Progreso",
      "status": "A"
    }
    ```

### Eliminar un Estado de Proyecto

-   **Método HTTP:** `DELETE`
-   **Ruta:** `/api/project-status/{id}`
-   **Descripción:** Elimina un estado de proyecto por su ID.
-   **Parámetros de Ruta:**
    -   `id` (Integer): ID único del estado.
-   **Respuesta Exitosa:** `204 No Content`

---

## Gestión de Tipos de Tarea (`/api/task-types`)

Recurso para administrar los tipos de tarea (ej. Diseño, Desarrollo, Prueba).

### Obtener todos los Tipos de Tarea

-   **Método HTTP:** `GET`
-   **Ruta:** `/api/task-types`
-   **Descripción:** Recupera una lista de todos los tipos de tarea.
-   **Ejemplo de Respuesta (200 OK):**
    ```json
    [
      {
        "id": 1,
        "description": "Análisis",
        "status": "A"
      },
      {
        "id": 2,
        "description": "Desarrollo",
        "status": "A"
      }
    ]
    ```

### Obtener un Tipo de Tarea por ID

-   **Método HTTP:** `GET`
-   **Ruta:** `/api/task-types/{id}`
-   **Descripción:** Recupera un tipo de tarea específico por su ID.
-   **Parámetros de Ruta:**
    -   `id` (Integer): ID único del tipo de tarea.
-   **Ejemplo de Respuesta (200 OK):**
    ```json
    {
      "id": 2,
      "description": "Desarrollo",
      "status": "A"
    }
    ```

### Crear un nuevo Tipo de Tarea

-   **Método HTTP:** `POST`
-   **Ruta:** `/api/task-types`
-   **Descripción:** Crea un nuevo tipo de tarea.
-   **Cuerpo de la Solicitud (application/json):**
    ```json
    {
      "description": "Pruebas",
      "status": "A"
    }
    ```
-   **Ejemplo de Respuesta (201 Created):**
    ```json
    {
      "id": 3,
      "description": "Pruebas",
      "status": "A"
    }
    ```

### Actualizar un Tipo de Tarea

-   **Método HTTP:** `PUT`
-   **Ruta:** `/api/task-types/{id}`
-   **Descripción:** Actualiza un tipo de tarea existente.
-   **Parámetros de Ruta:**
    -   `id` (Integer): ID único del tipo de tarea.
-   **Cuerpo de la Solicitud (application/json):**
    ```json
    {
      "description": "Testing",
      "status": "A"
    }
    ```
-   **Ejemplo de Respuesta (200 OK):**
    ```json
    {
      "id": 3,
      "description": "Testing",
      "status": "A"
    }
    ```

### Eliminar un Tipo de Tarea

-   **Método HTTP:** `DELETE`
-   **Ruta:** `/api/task-types/{id}`
-   **Descripción:** Elimina un tipo de tarea por su ID.
-   **Parámetros de Ruta:**
    -   `id` (Integer): ID único del tipo de tarea.
-   **Respuesta Exitosa:** `204 No Content`

---

## Gestión de Niveles de Experiencia (`/api/experience-levels`)

Recurso para administrar los niveles de experiencia de los empleados (ej. Junior, Senior).

### Obtener todos los Niveles de Experiencia

-   **Método HTTP:** `GET`
-   **Ruta:** `/api/experience-levels`
-   **Descripción:** Recupera una lista de todos los niveles de experiencia.
-   **Ejemplo de Respuesta (200 OK):**
    ```json
    [
      {
        "id": 1,
        "description": "Junior",
        "value": 1,
        "status": "A"
      },
      {
        "id": 2,
        "description": "Semi-Senior",
        "value": 2,
        "status": "A"
      }
    ]
    ```

### Obtener un Nivel de Experiencia por ID

-   **Método HTTP:** `GET`
-   **Ruta:** `/api/experience-levels/{id}`
-   **Descripción:** Recupera un nivel de experiencia específico por su ID.
-   **Parámetros de Ruta:**
    -   `id` (Integer): ID único del nivel de experiencia.
-   **Ejemplo de Respuesta (200 OK):**
    ```json
    {
      "id": 1,
      "description": "Junior",
      "value": 1,
      "status": "A"
    }
    ```

### Crear un nuevo Nivel de Experiencia

-   **Método HTTP:** `POST`
-   **Ruta:** `/api/experience-levels`
-   **Descripción:** Crea un nuevo nivel de experiencia.
-   **Cuerpo de la Solicitud (application/json):**
    ```json
    {
      "description": "Senior",
      "value": 3,
      "status": "A"
    }
    ```
-   **Ejemplo de Respuesta (201 Created):**
    ```json
    {
      "id": 3,
      "description": "Senior",
      "value": 3,
      "status": "A"
    }
    ```

### Actualizar un Nivel de Experiencia

-   **Método HTTP:** `PUT`
-   **Ruta:** `/api/experience-levels/{id}`
-   **Descripción:** Actualiza un nivel de experiencia existente.
-   **Parámetros de Ruta:**
    -   `id` (Integer): ID único del nivel.
-   **Cuerpo de la Solicitud (application/json):**
    ```json
    {
      "description": "Semi-Senior",
      "value": 2,
      "status": "I"
    }
    ```
-   **Ejemplo de Respuesta (200 OK):**
    ```json
    {
      "id": 2,
      "description": "Semi-Senior",
      "value": 2,
      "status": "I"
    }
    ```

### Eliminar un Nivel de Experiencia

-   **Método HTTP:** `DELETE`
-   **Ruta:** `/api/experience-levels/{id}`
-   **Descripción:** Elimina un nivel de experiencia por su ID.
-   **Parámetros de Ruta:**
    -   `id` (Integer): ID único del nivel.
-   **Respuesta Exitosa:** `204 No Content`

---

## Gestión de Factores de Tiempo (`/api/time-factors`)

Recurso para administrar los factores de tiempo usados en cálculos.

### Obtener todos los Factores de Tiempo

-   **Método HTTP:** `GET`
-   **Ruta:** `/api/time-factors`
-   **Descripción:** Recupera una lista de todos los factores de tiempo.
-   **Ejemplo de Respuesta (200 OK):**
    ```json
    [
      {
        "id": 1,
        "description": "Entrega a tiempo",
        "value": 1.0,
        "status": "A"
      },
      {
        "id": 2,
        "description": "Entrega con retraso leve",
        "value": 0.8,
        "status": "A"
      }
    ]
    ```

### Obtener un Factor de Tiempo por ID

-   **Método HTTP:** `GET`
-   **Ruta:** `/api/time-factors/{id}`
-   **Descripción:** Recupera un factor de tiempo específico por su ID.
-   **Parámetros de Ruta:**
    -   `id` (Integer): ID único del factor de tiempo.
-   **Ejemplo de Respuesta (200 OK):**
    ```json
    {
      "id": 1,
      "description": "Entrega a tiempo",
      "value": 1.0,
      "status": "A"
    }
    ```

### Crear un nuevo Factor de Tiempo

-   **Método HTTP:** `POST`
-   **Ruta:** `/api/time-factors`
-   **Descripción:** Crea un nuevo factor de tiempo.
-   **Cuerpo de la Solicitud (application/json):**
    ```json
    {
      "description": "Entrega adelantada",
      "value": 1.2,
      "status": "A"
    }
    ```
-   **Ejemplo de Respuesta (201 Created):**
    ```json
    {
      "id": 3,
      "description": "Entrega adelantada",
      "value": 1.2,
      "status": "A"
    }
    ```

### Actualizar un Factor de Tiempo

-   **Método HTTP:** `PUT`
-   **Ruta:** `/api/time-factors/{id}`
-   **Descripción:** Actualiza un factor de tiempo existente.
-   **Parámetros de Ruta:**
    -   `id` (Integer): ID único del factor.
-   **Cuerpo de la Solicitud (application/json):**
    ```json
    {
      "description": "Entrega con retraso mayor",
      "value": 0.5,
      "status": "A"
    }
    ```
-   **Ejemplo de Respuesta (200 OK):**
    ```json
    {
      "id": 4,
      "description": "Entrega con retraso mayor",
      "value": 0.5,
      "status": "A"
    }
    ```

### Eliminar un Factor de Tiempo

-   **Método HTTP:** `DELETE`
-   **Ruta:** `/api/time-factors/{id}`
-   **Descripción:** Elimina un factor de tiempo por su ID.
-   **Parámetros de Ruta:**
    -   `id` (Integer): ID único del factor.
-   **Respuesta Exitosa:** `204 No Content`

---

## Gestión de Factores de Utilidad (`/api/utility-factors`)

Recurso para administrar los factores de utilidad usados en cálculos de complejidad.

### Obtener todos los Factores de Utilidad

-   **Método HTTP:** `GET`
-   **Ruta:** `/api/utility-factors`
-   **Descripción:** Recupera una lista de todos los factores de utilidad.
-   **Ejemplo de Respuesta (200 OK):**
    ```json
    [
      {
        "id": 1,
        "description": "Tecnología nueva",
        "status": "A"
      },
      {
        "id": 2,
        "description": "Integración con sistema legado",
        "status": "A"
      }
    ]
    ```

### Obtener un Factor de Utilidad por ID

-   **Método HTTP:** `GET`
-   **Ruta:** `/api/utility-factors/{id}`
-   **Descripción:** Recupera un factor de utilidad específico por su ID.
-   **Parámetros de Ruta:**
    -   `id` (Integer): ID único del factor de utilidad.
-   **Ejemplo de Respuesta (200 OK):**
    ```json
    {
      "id": 1,
      "description": "Tecnología nueva",
      "status": "A"
    }
    ```

### Crear un nuevo Factor de Utilidad

-   **Método HTTP:** `POST`
-   **Ruta:** `/api/utility-factors`
-   **Descripción:** Crea un nuevo factor de utilidad.
-   **Cuerpo de la Solicitud (application/json):**
    ```json
    {
      "description": "Alto riesgo de seguridad",
      "status": "A"
    }
    ```
-   **Ejemplo de Respuesta (201 Created):**
    ```json
    {
      "id": 3,
      "description": "Alto riesgo de seguridad",
      "status": "A"
    }
    ```

### Actualizar un Factor de Utilidad

-   **Método HTTP:** `PUT`
-   **Ruta:** `/api/utility-factors/{id}`
-   **Descripción:** Actualiza un factor de utilidad existente.
-   **Parámetros de Ruta:**
    -   `id` (Integer): ID único del factor.
-   **Cuerpo de la Solicitud (application/json):**
    ```json
    {
      "description": "Requerimientos poco claros",
      "status": "A"
    }
    ```
-   **Ejemplo de Respuesta (200 OK):**
    ```json
    {
      "id": 4,
      "description": "Requerimientos poco claros",
      "status": "A"
    }
    ```

### Eliminar un Factor de Utilidad

-   **Método HTTP:** `DELETE`
-   **Ruta:** `/api/utility-factors/{id}`
-   **Descripción:** Elimina un factor de utilidad por su ID.
-   **Parámetros de Ruta:**
    -   `id` (Integer): ID único del factor.
-   **Respuesta Exitosa:** `204 No Content`
