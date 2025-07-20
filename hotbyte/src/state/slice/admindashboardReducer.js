import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

export const fetchDashboardData = createAsyncThunk(
  'admindashboard/fetchDashboardData',
  async (_, { rejectWithValue }) => {
    try {
      const [usersRes, restaurantsRes, ordersRes, menuRes] = await Promise.all([
        axios.get('http://localhost:9002/api/users'),
        axios.get('http://localhost:9002/api/restaurant/all'),
        axios.get('http://localhost:9002/api/order/orders'),
        axios.get('http://localhost:9002/api/menu/menu'),
      ]);

      const orders = Array.isArray(ordersRes.data) ? ordersRes.data : [];

      const totalRevenue = orders.reduce((sum, order) => sum + parseFloat(order.totalPrice || 0), 0);
      const avgOrderValue = orders.length > 0 ? totalRevenue / orders.length : 0;
      const commission = totalRevenue * 0.10;

      return {
        users: Array.isArray(usersRes.data) ? usersRes.data.length : 0,
        restaurants: Array.isArray(restaurantsRes.data) ? restaurantsRes.data.length : 0,
        orders: orders.length,
        revenue: totalRevenue,
        menuItems: Array.isArray(menuRes.data) ? menuRes.data.length : 0,
        avgOrderValue,
        commission,
        recentOrders: orders.slice(-5).reverse().map(order => ({
          id: order.orderId,
          name: order.customerName || `Order #${order.orderId ?? 'N/A'}`,
          restaurant: order.restaurantName || 'N/A',
          amount: parseFloat(order.totalPrice || 0),
          status: order.orderStatus?.toLowerCase() || 'pending'
        })),
        restaurantStatus: restaurantsRes.data.map(r => ({
          name: r.restaurantName,
          address: r.address?.street || 'N/A',
          rating: r.averageRating || 4.5
        }))
      };
    } catch (err) {
      return rejectWithValue(err.response?.data || 'Error fetching dashboard data');
    }
  }
);

const admindashboardSlice = createSlice({
  name: 'admindashboard',
  initialState: {
    users: 0,
    restaurants: 0,
    orders: 0,
    revenue: 0,
    menuItems: 0,
    avgOrderValue: 0,
    commission: 0,
    recentOrders: [],
    restaurantStatus: [],
    loading: false,
    error: null
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchDashboardData.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(fetchDashboardData.fulfilled, (state, action) => {
        Object.assign(state, { ...action.payload, loading: false });
      })
      .addCase(fetchDashboardData.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
      });
  }
});

export default admindashboardSlice.reducer;
