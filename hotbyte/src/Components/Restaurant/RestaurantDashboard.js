// RestaurantDashboard.js
import React, { useState, useEffect } from 'react';
import './RestaurantDashboard.css';
import { Link, useNavigate } from 'react-router-dom';




const RestaurantDashboard = () => {
  const [recentOrders, setRecentOrders] = useState([]);

    const navigate = useNavigate();

    useEffect(() => {
  const storedOrders = JSON.parse(localStorage.getItem('restaurant_orders')) || [];
  setRecentOrders(storedOrders.slice(-3)); // Show last 3
}, []);

    

  return (
    <div className="restaurant-dashboard-container">
      <h2>Restaurant Dashboard</h2>

      {/* Top 4 Stats Cards */}
      <div className="stats-cards">
        <div className="stat-card">
          <i className="pi pi-shopping-bag stat-icon blue" />
          <h4>Total Orders</h4>
          <p className="stat-value">1</p>
        </div>
        <div className="stat-card">
          <i className="pi pi-list stat-icon green" />
          <h4>Menu Items</h4>
          <p className="stat-value">2</p>
        </div>
        <div className="stat-card">
          <i className="pi pi-dollar stat-icon orange" />
          <h4>Today's Revenue</h4>
          <p className="stat-value">$46.97</p>
        </div>
        <div className="stat-card">
          <i className="pi pi-clock stat-icon purple" />
          <h4>Avg Prep Time</h4>
          <p className="stat-value">18 min</p>
        </div>
      </div>

      {/* Middle Widgets */}
      <div className="middle-widgets">
        <div className="recent-orders">
          <div className="widget-header">
            <h3>Recent Orders</h3>
            <Link to="/restaurant/orders">View All</Link>
          </div>
          {recentOrders.length === 0 ? (
            <p>No recent orders</p>
              ) : (
              recentOrders.map(order => (
            <div className="order-card" key={order.id}>
            <div>
        <strong>Order #{order.id}</strong>
        <p>{order.items.length} items</p>
      </div>
      <div>
        <strong>₹{order.total.toFixed(2)}</strong>
        <span className={`badge ${order.status}`}>{order.status}</span>
      </div>
    </div>
  ))
)}

        </div>

        <div className="popular-items">
          <div className="widget-header">
            <h3>Popular Items</h3>
            <a href="#">Manage Menu</a>
          </div>
          <div className="menu-item">
            <img src="/butter-chicken.jpg" alt="Butter Chicken" />
            <div className="item-info">
              <strong>Butter Chicken</strong>
              <p>Main Course</p>
            </div>
            <div className="item-right">
              <strong>$18.99</strong>
              <p>25 min</p>
            </div>
          </div>
          <div className="menu-item">
            <img src="/veg-biryani.jpg" alt="Vegetable Biryani" />
            <div className="item-info">
              <strong>Vegetable Biryani</strong>
              <p>Main Course</p>
            </div>
            <div className="item-right">
              <strong>$14.99</strong>
              <p>30 min</p>
            </div>
          </div>
        </div>
      </div>

      {/* Quick Actions */}
      <div className="quick-actions">
        <h3>Quick Actions</h3>
        <div className="actions-buttons">
          <button className="btn-primary">Manage Menu</button>
         <Link to="/restaurant/orders" className="btn-grey">
            <i className="pi pi-shopping-bag" /> View Orders
          </Link>

         <button className="btn-grey" onClick={() => navigate('/restaurant/profile')}>
            <i className="pi pi-user-edit" /> Profile Settings
        </button>

        </div>
      </div>
    </div>
  );
};

export default RestaurantDashboard;
