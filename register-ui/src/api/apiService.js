const API_BASE_URL = 'http://localhost:8080/api';

/**
 * Maneja la respuesta de la API, lanzando un error si la respuesta no es OK.
 * @param {Response} response - La respuesta del fetch.
 * @returns {Promise<any>} - El cuerpo de la respuesta en formato JSON.
 */
const handleResponse = async (response) => {
  if (!response.ok) {
    const errorData = await response.json().catch(() => ({ message: 'Error de red o respuesta no JSON' }));
    const errorMessage = errorData.message || (errorData.errors ? errorData.errors.join(', ') : 'Ocurrió un error desconocido');
    throw new Error(errorMessage);
  }
  // Retorna null si no hay contenido (ej. para DELETE 204)
  if (response.status === 204) {
    return null;
  }
  return response.json();
};

/**
 * Objeto con métodos para interactuar con la API de catálogos.
 */
const catalogService = {
  /**
   * Obtiene todos los registros de un endpoint.
   * @param {string} endpoint - El endpoint del catálogo (ej. '/client-types').
   */
  getAll: (endpoint) => {
    return fetch(`${API_BASE_URL}${endpoint}`).then(handleResponse);
  },

  /**
   * Obtiene un registro por su ID.
   * @param {string} endpoint - El endpoint del catálogo.
   * @param {number|string} id - El ID del registro.
   */
  getById: (endpoint, id) => {
    return fetch(`${API_BASE_URL}${endpoint}/${id}`).then(handleResponse);
  },

  /**
   * Crea un nuevo registro.
   * @param {string} endpoint - El endpoint del catálogo.
   * @param {object} data - El objeto con los datos del nuevo registro.
   */
  create: (endpoint, data) => {
    return fetch(`${API_BASE_URL}${endpoint}`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(data),
    }).then(handleResponse);
  },

  /**
   * Actualiza un registro existente.
   * @param {string} endpoint - El endpoint del catálogo.
   * @param {number|string} id - El ID del registro a actualizar.
   * @param {object} data - El objeto con los datos actualizados.
   */
  update: (endpoint, id, data) => {
    return fetch(`${API_BASE_URL}${endpoint}/${id}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(data),
    }).then(handleResponse);
  },

  /**
   * Elimina un registro por su ID.
   * @param {string} endpoint - El endpoint del catálogo.
   * @param {number|string} id - El ID del registro a eliminar.
   */
  remove: (endpoint, id) => {
    return fetch(`${API_BASE_URL}${endpoint}/${id}`, {
      method: 'DELETE',
    }).then(handleResponse);
  },
};

export default catalogService;
