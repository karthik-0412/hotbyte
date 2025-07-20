import React, { useState, useRef } from 'react';
import axios from 'axios';
import { useNavigate, Link } from 'react-router-dom';
import { InputText } from 'primereact/inputtext';
import { Password } from 'primereact/password';
import { Dropdown } from 'primereact/dropdown';
import { Button } from 'primereact/button';
import { Card } from 'primereact/card';
import { Toast } from 'primereact/toast';
import 'primereact/resources/themes/lara-light-blue/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';

export default function Register() {
  const [registerData, setRegisterData] = useState({
    username: '', password: '', confirmPassword: '', role: ''
  });
  const navigate = useNavigate();
  const toast = useRef(null);

  const handleChange = (e) => {
    const { name, value } = e.target ? e.target : { name: 'role', value: e.value };
    setRegisterData({ ...registerData, [name]: value });
  };

  const handleRegister = async (e) => {
    e.preventDefault();

    // Frontend validation for password match
    if (registerData.password !== registerData.confirmPassword) {
      toast.current.show({
        severity: 'error',
        summary: 'Password Mismatch',
        detail: 'Password and Confirm Password do not match',
        life: 3000
      });
      return;
    }

    try {
      await axios.post('http://localhost:8085/api/auth/register', {
        username: registerData.username,
        password: registerData.password,
        role: registerData.role
      });
      toast.current.show({
        severity: 'success',
        summary: 'Registration Successful',
        detail: 'You can now login',
        life: 3000
      });
      setTimeout(() => navigate('/'), 3000);
    } catch (err) {
      toast.current.show({
        severity: 'error',
        summary: 'Registration Failed',
        detail: 'Please try again',
        life: 3000
      });
    }
  };

  const roleOptions = [
    { label: 'USER', value: 'USER' },
    { label: 'ADMIN', value: 'ADMIN' }
  ];

  const containerStyle = {
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    height: '100vh',
    background: 'linear-gradient(135deg, #4CAF50, #2E7D32)',
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
    color: '#2E7D32'
  };

  const inputGroupStyle = { marginBottom: '20px' };

  const linkStyle = {
    textAlign: 'center',
    marginTop: '15px',
    color: '#2E7D32'
  };

  return (
    <div style={containerStyle}>
      <Toast ref={toast} />
      <Card style={cardStyle}>
        <h2 style={headingStyle}>Register</h2>
        <form onSubmit={handleRegister}>
          <div className="p-field" style={inputGroupStyle}>
            <span className="p-input-icon-left" style={{ width: '100%' }}>
              <i className="pi pi-user" />
              <InputText
                name="username"
                placeholder="Username"
                value={registerData.username}
                onChange={handleChange}
                required
                style={{ width: '85%', marginLeft: '30px' }}
              />
            </span>
          </div>

          <div className="p-field" style={inputGroupStyle}>
            <span className="p-input-icon-left" style={{ width: '100%' }}>
              <i className="pi pi-lock" />
              <Password
                name="password"
                placeholder="Password"
                value={registerData.password}
                onChange={handleChange}
                required
                toggleMask
                feedback={false}
                style={{ width: '100%', marginLeft: '30px' }}
              />
            </span>
          </div>

          <div className="p-field" style={inputGroupStyle}>
            <span className="p-input-icon-left" style={{ width: '100%' }}>
              <i className="pi pi-lock" />
              <Password
                name="confirmPassword"
                placeholder="Confirm Password"
                value={registerData.confirmPassword}
                onChange={handleChange}
                required
                toggleMask
                feedback={false}
                style={{ width: '100%', marginLeft: '30px' }}
              />
            </span>
          </div>

          <div className="p-field" style={inputGroupStyle}>
            <Dropdown
              name="role"
              value={registerData.role}
              options={roleOptions}
              onChange={handleChange}
              placeholder="Select Role"
              required
              style={{ width: '95%' }}
            />
          </div>

          <Button
            label="Register"
            className="p-button-success"
            type="submit"
            style={{ width: '95%', marginBottom: '10px' }}
          />

          <p style={linkStyle}>
            Already have an account? <Link to="/" style={{ color: '#4CAF50' }}>Login</Link>
          </p>
        </form>
      </Card>
    </div>
  );
}
