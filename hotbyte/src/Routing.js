import React from 'react';
import { Route, Routes } from 'react-router-dom';
import Home from './Pages/Home'; // Adjust the import path as necessary
import LoginPage from './Pages/LoginPage';
import Signup from './Pages/Signup'; // Adjust the import path as necessary
import Terms from './Pages/Terms'; // Adjust the import path as necessary
import About from './Pages/About'; // Uncomment if you have an About page
import Contact from './Pages/Contact'; // Uncomment if you have a Contact page
import MenuPage from './Pages/MenuPage';
import ProfileSettingsPage from './Pages/Customer/ProfileSettingsPage';
import MyOrders from './Components/Customer/MyOrders';
import CheckoutPage from './Components/Customer/CheckoutPage';
import CartComponent from './Components/Customer/CartComponent';
import UserManagement from './Components/Admin/UserManagement';
import RestaurantManagement from './Components/Admin/RestaurantMangement';
// import OrderManagement from './Components/Admin/OrderManagement';
import CouponManagement from './Components/Admin/CouponManagement';
import CategoryManagement from './Components/Admin/CategoryManagement';
import MenuItemsPage from './Pages/Admin/MenuItemsPage';
import AdminDashboard from './Components/Admin/AdminDashboard';
// import RestaurantMenuManagement from './Components/Restaurant/RestaurantMenuManagement';
import ForgotPassword from './Components/ForgetPassword';
import Item from './Components/Item';
import AdminDashboardPage from './Pages/Admin/AdminDashboardPage';
import CouponPage from './Pages/Admin/CouponPage';
import CategoryManagePage from './Pages/Admin/CategoryManagePage';
import UserManagePage from './Pages/Admin/UserManagePage';
import OrderManagePage from './Pages/Admin/OrderManagePage';
import RestaurantManagePage from './Pages/Admin/RestaurantManagePage';
import OrderManagement from './Components/Restaurant/RestaurantOrderManagement';
// import RestaurantDashboard from './Components/Restaurant/RestaurantDashboard';
import RestaurantDashboardPage from './Pages/Restaurant/RestaurantDashboardPage';
import RestaurantMenuPage from './Pages/Restaurant/RestaurantMenuPage';
import RestaurantCategoryPage from './Pages/Restaurant/RestaurantCategoryPage';
import RestaurantOrderPage from './Pages/Restaurant/RestaurantOrderPage';
import RestaurantProfileManagementPage from './Pages/Restaurant/RestaurantProfileManagementPage';
import ResetPassword from './Components/ResetPassword';




const Rounting = () => {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/login" element={<LoginPage />} />
      <Route path="/signup" element={<Signup/>}/>
      <Route path="/terms" element={<Terms />} />
      <Route path="/about" element={<About />} /> 
      <Route path="/contact" element={<Contact />} /> 
      <Route path='/menu' element={<MenuPage/>}/>
      <Route path='/profile' element={<ProfileSettingsPage/>}/>
      <Route path="/orders" element={<MyOrders/>}/>
      <Route path='/checkout' element={<CheckoutPage/>}/>
      <Route path='/cart' element={<CartComponent/>}/>
      <Route path='/usermanage' element={<UserManagePage/>}/>
      <Route path='/restaurant' element={<RestaurantManagePage/>}/>
      <Route path='/ordermanage' element={<OrderManagePage/>}/>
      <Route path='/coupons' element={<CouponPage/>}/>
      <Route path='/category' element={<CategoryManagePage/>}/>
      <Route path='/menumanage' element={<MenuItemsPage/>}/>
      <Route path='/admindashboard' element={<AdminDashboardPage/>}/>
      <Route path='/menures' element={<RestaurantMenuPage/>}/>
      <Route path='/forgot-password' element={<ForgotPassword/>}/>
      <Route path='/item' element={<Item/>}/>
      <Route path='/resdash' element={<RestaurantDashboardPage/>}/>
      <Route path='/rescat' element={<RestaurantCategoryPage/>}/>
      <Route path='/resorder' element={<RestaurantOrderPage/>}/>
      <Route path='/resprofile' element={<RestaurantProfileManagementPage/>}/>
      <Route path='/reset-password' element={<ResetPassword/>}/>
      {/* <Route path='/' */}
    </Routes> 
  );
}
export default Rounting;