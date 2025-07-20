import React, { useState, useRef } from 'react';
import axios from 'axios';
import { useNavigate, Link } from 'react-router-dom';
import { InputText } from 'primereact/inputtext';
import { Password } from 'primereact/password';
import { Button } from 'primereact/button';
import { Card } from 'primereact/card';
import { Toast } from 'primereact/toast';
import 'primereact/resources/themes/lara-light-blue/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';

export default function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();
  const toast = useRef(null);

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const res = await axios.post('http://localhost:8085/api/auth/login', { username, password });
      localStorage.setItem('token', res.data);
      navigate('/books');
    } catch (err) {
      toast.current.show({
        severity: 'error',
        summary: 'Login Failed',
        detail: 'Invalid credentials',
        life: 3000
      });
    }
  };

  const containerStyle = {
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    height: '100vh',
    background: 'linear-gradient(135deg, #2196F3, #1565C0)',
    padding: '20px'
  };

  const cardStyle = {
    width: '100%',
    maxWidth: '400px',
    padding: '20px',
    borderRadius: '10px'
  };

  const headingStyle = {
    textAlign: 'center',
    marginBottom: '20px',
    color: '#1565C0'
  };

  const inputGroupStyle = { marginBottom: '20px' };

  const linkStyle = {
    textAlign: 'center',
    marginTop: '15px',
    color: '#1565C0'
  };

  return (
    <div style={containerStyle}>
      <Toast ref={toast} />
      <Card style={cardStyle}>
        <h2 style={headingStyle}>Login</h2>
        <form onSubmit={handleLogin}>
          <div className="p-field" style={inputGroupStyle}>
            <span className="p-input-icon-left" style={{ width: '100%' }}>
              <i className="pi pi-user" />
              <InputText
                placeholder="Username"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
                required
                style={{ width: '85%', marginLeft: '30px' }}
              />
            </span>
          </div>

          <div className="p-field" style={inputGroupStyle}>
            <span className="p-input-icon-left" style={{ width: '100%' }}>
              <i className="pi pi-lock" />
              <Password
                placeholder="Password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
                toggleMask
                feedback={false}
                style={{ width: '100%', marginLeft: '30px' }}
              />
            </span>
          </div>

          <Button
            label="Login"
            className="p-button-primary"
            type="submit"
            style={{ width: '96%', marginBottom: '10px' }}
          />

          <p style={linkStyle}>
            Don't have an account? <Link to="/register" style={{ color: '#2196F3' }}>Signup</Link>
          </p>
        </form>
      </Card>
    </div>
  );
}
