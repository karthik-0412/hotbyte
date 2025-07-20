package Dao;

import model.*;

public interface CustomerDao1 {
	public void addCustomer(Customer c);
	public void showAll();
	public void searchByact(int ac);
	public void searchByName(String name);
	void RemoveByact(int ac);
    void updateByact(int ac,int bal);
}
