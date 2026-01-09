import React, { createContext, useContext, useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { getProfile, login, signup } from '../services/authApi';

const AuthContext = createContext(null);

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [token, setToken] = useState(() => localStorage.getItem('token'));
  const [loading, setLoading] = useState(!!localStorage.getItem('token'));
  const navigate = useNavigate();

  useEffect(() => {
    const bootstrap = async () => {
      if (!token) {
        setLoading(false);
        return;
      }
      try {
        const profile = await getProfile();
        setUser(profile);
      } catch {
        setToken(null);
        localStorage.removeItem('token');
      } finally {
        setLoading(false);
      }
    };
    bootstrap();
  }, [token]);

  const handleLogin = async (credentials) => {
    const res = await login(credentials);
    setToken(res.token);
    localStorage.setItem('token', res.token);
    setUser({ email: res.email, fullName: res.fullName });
    navigate('/');
  };

  const handleSignup = async (data) => {
    const res = await signup(data);
    setToken(res.token);
    localStorage.setItem('token', res.token);
    setUser({ email: res.email, fullName: res.fullName });
    navigate('/');
  };

  const logout = () => {
    setUser(null);
    setToken(null);
    localStorage.removeItem('token');
    navigate('/login');
  };

  const value = { user, token, loading, login: handleLogin, signup: handleSignup, logout };

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
};

export const useAuthContext = () => useContext(AuthContext);


