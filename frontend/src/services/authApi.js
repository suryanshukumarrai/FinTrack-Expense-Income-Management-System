import api from './apiClient';

export const login = async (credentials) => {
  const { data } = await api.post('/auth/login', credentials);
  return data.data;
};

export const signup = async (payload) => {
  const { data } = await api.post('/auth/signup', payload);
  return data.data;
};

export const getProfile = async () => {
  // Placeholder for future /me endpoint
  return null;
};


