import React from 'react';
import AdminLayout from '../../Components/Admin/AdminLayout';
import MenuManagement from '../../Components/Admin/MenuManagement';

// Or put JSX inline

export default function MenuItemsPage() {
  return (
    <AdminLayout>
      <MenuManagement /> {/* Or directly paste menu JSX here */}
    </AdminLayout>
  );
}
