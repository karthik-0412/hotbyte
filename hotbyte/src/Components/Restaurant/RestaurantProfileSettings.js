import React, { useRef, useState, useEffect } from 'react';
import { InputText } from 'primereact/inputtext';
import { InputTextarea } from 'primereact/inputtextarea';
import { Toast } from 'primereact/toast';
import { Button } from 'primereact/button';
import { useRestaurantProfile } from './RestaurantProfileContext';
import axios from 'axios';

const RestaurantProfileSettings = () => {
  const toast = useRef(null);
  const { restaurantProfile, setRestaurantProfile } = useRestaurantProfile();

  const [restaurantName, setRestaurantName] = useState('');
  const [email, setEmail] = useState('');
  const [phone, setPhone] = useState('');
  const [address, setAddress] = useState('');
  const [description, setDescription] = useState('');

  // ✅ Fetch restaurant profile
  useEffect(() => {
    const fetchRestaurantProfile = async () => {
      try {
        const profileToken = localStorage.getItem("token");
        if (!profileToken) throw new Error("No token found. Please login again.");

        const headers = { Authorization: `Bearer ${profileToken}` };
        const response = await axios.get('http://localhost:9002/api/restaurant/profile', { headers });
        const data = response.data;

        setRestaurantName(data.restaurantName || '');
        setEmail(data.userdto?.email || '');
        setPhone(data.userdto?.phoneNumber || '');
        setAddress(
          `${data.addressdto?.street || ''}, ${data.addressdto?.city || ''}, ${data.addressdto?.state || ''} - ${data.addressdto?.pincode || ''}`
        );
        setDescription(data.description || '');
        setRestaurantProfile(data);
      } catch (error) {
        console.error("Fetch error:", error);
        toast.current?.show({
          severity: 'error',
          summary: 'Error',
          detail: 'Failed to fetch restaurant profile.',
          life: 3000
        });
      }
    };

    fetchRestaurantProfile();
  }, [setRestaurantProfile]);

  // ✅ Save/Update profile
  const handleSave = async () => {
    try {
      const updateToken = localStorage.getItem("token");
      if (!updateToken || !restaurantProfile?.restaurantId) {
        throw new Error("Missing token or restaurant info.");
      }

      const [street = '', city = '', stateAndPincode = ''] = address.split(',');
      const [state = '', pincode = ''] = stateAndPincode.split('-');

      const payload = {
        restaurantName,
        description,
        addressdto: {
          addressId: restaurantProfile.addressdto?.addressId,
          street: street.trim(),
          city: city.trim(),
          state: state.trim(),
          pincode: pincode.trim()
        },
        userdto: {
          userId: restaurantProfile.userdto?.userId,
          name: restaurantProfile.userdto?.name,
          email: email.trim(),
          phoneNumber: phone.trim()
        }
      };

      const headers = {
        Authorization: `Bearer ${updateToken}`,
        'Content-Type': 'application/json'
      };

      const response = await axios.put(
        `http://localhost:9002/api/restaurant/${restaurantProfile.restaurantId}`,
        payload,
        { headers }
      );

      setRestaurantProfile(response.data);

      toast.current.show({
        severity: 'success',
        summary: 'Success',
        detail: 'Restaurant profile updated successfully!',
        life: 3000
      });
    } catch (error) {
      console.error("Update error:", error);
      toast.current?.show({
        severity: 'error',
        summary: 'Error',
        detail: 'Failed to update profile.',
        life: 3000
      });
    }
  };

  return (
    <div className="profile-settings-container" style={{ maxWidth: 500, margin: "40px auto", background: "#fff8ee", borderRadius: 16, padding: 32, boxShadow: "0 4px 16px rgba(0,0,0,0.08)" }}>
      <Toast ref={toast} />
      <h2 style={{ fontSize: "2rem", marginBottom: 16 }}>Restaurant Profile</h2>

      {/* Profile Summary Card */}
      <div className="profile-card" style={{ display: "flex", alignItems: "center", gap: 16, marginBottom: 24 }}>
        <div className="avatar-circle" style={{ background: "#ff5722", borderRadius: "50%", width: 56, height: 56, display: "flex", alignItems: "center", justifyContent: "center" }}>
          <i className="pi pi-building" style={{ fontSize: '2rem', color: "#fff" }}></i>
        </div>
        <div>
          <h3 style={{ margin: 0, fontSize: "1.2rem" }}>{restaurantProfile.restaurantName || 'Your Restaurant'}</h3>
          <p style={{ margin: 0, color: "#555" }}>{restaurantProfile.userdto?.email || 'restaurant@hotbyte.com'}</p>
        </div>
      </div>

      {/* Restaurant Info Section */}
      <section>
        <h4 style={{ marginBottom: 12 }}>Restaurant Information</h4>
        <div className="form-row" style={{ display: "flex", gap: 12, marginBottom: 12 }}>
          <InputText style={{ flex: 1, fontSize: "1rem", padding: "10px" }} placeholder="Restaurant Name" value={restaurantName} onChange={(e) => setRestaurantName(e.target.value)} />
          <InputText style={{ flex: 1, fontSize: "1rem", padding: "10px" }} placeholder="Email Address" value={email} onChange={(e) => setEmail(e.target.value)} />
        </div>
        <div className="form-row" style={{ marginBottom: 12 }}>
          <InputText style={{ width: "100%", fontSize: "1rem", padding: "10px" }} placeholder="Phone Number" value={phone} onChange={(e) => setPhone(e.target.value)} />
        </div>
        <InputTextarea
          style={{ width: "100%", fontSize: "1rem", padding: "10px", marginBottom: 12 }}
          placeholder="Restaurant Address"
          rows={2}
          value={address}
          onChange={(e) => setAddress(e.target.value)}
        />
        <InputTextarea
          style={{ width: "100%", fontSize: "1rem", padding: "10px", marginBottom: 12 }}
          placeholder="Restaurant Description"
          rows={3}
          value={description}
          onChange={(e) => setDescription(e.target.value)}
        />
      </section>

      {/* Save Button */}
      <div className="save-btn-container" style={{ textAlign: "center", marginTop: 18 }}>
        <Button icon="pi pi-check" label="Save Changes" className="custom-save-btn" style={{ fontSize: "1.1rem", padding: "12px 32px", borderRadius: 8 }} onClick={handleSave} />
      </div>
    </div>
  );
};

export default RestaurantProfileSettings;