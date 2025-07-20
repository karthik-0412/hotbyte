package Mavenproj;

import Dao.CustomerDao;
import model.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Customer obj = new Customer(101,"Ajay",3000,"deposit");
    	
    	CustomerDao dao= new CustomerDao();
//    	dao.addCustomer(obj);
//    	dao.showAll();
//        dao.searchByName("ajay");
//    	dao.searchByact(101);
//        dao.RemoveByact(101);
        dao.updateByact(101,5000);
//        System.out.println(obj.toString());
    }
}
