const handleResponse = async (response) => {
  if (!response.ok) {
    const error = await response.json().catch(() => ({ message: response.statusText }))
    throw new Error(error.message || 'Error en la petición a la API')
  }
  if (response.status === 204 || response.status === 200 && response.headers.get('content-length') === '0') {
    return null
  }
  return response.json()
}

const apiService = {
  getAll: async (endpoint) => {
    const response = await fetch(endpoint)
    return handleResponse(response)
  },

  create: async (endpoint, data) => {
    const response = await fetch(endpoint, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(data),
    })
    return handleResponse(response)
  },

  update: async (endpoint, id, data) => {
    const response = await fetch(`${endpoint}/${id}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(data),
    })
    return handleResponse(response)
  },

  remove: async (endpoint, id) => {
    const response = await fetch(`${endpoint}/${id}`, {
      method: 'DELETE',
    })
    return handleResponse(response)
  },
}

export default apiService
