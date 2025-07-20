package org.springexample;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        SessionFactory sessionFactory = new Configuration().
//                configure("hiber.config.xml").
//                addAnnotatedClass(Student.class).
//                addAnnotatedClass(Address.class).buildSessionFactory();
//        Session session = sessionFactory.openSession();
//        Transaction txt = session.beginTransaction();
//        Address a1 = new Address();
//        a1.setAddressid(2);
//        a1.setCity("Mumbai");
////        session.save(a1);
//        Student s1 = new Student();
//        s1.setRollno(102);
//        s1.setName("Aarya");
//        s1.setAddress(a1);
//
//        session.save(s1);
//        txt.commit();
//        SessionFactory sessionFactory = new Configuration().
//                configure("hiber.config.xml").
//                addAnnotatedClass(Employee.class).
//                addAnnotatedClass(Department.class).buildSessionFactory();
//         Session session = sessionFactory.openSession();
//         Transaction txt = session.beginTransaction();
//        Employee e1= new Employee(101,"asha",3000.0);
//        Employee e2= new Employee(102,"jay",7000.0);
//
//
//        List<Employee> list= new ArrayList();
//        list.add(e1);
//        list.add(e2);
//
//        Department ojbD= new Department();
//        ojbD.setdCode(1);
//        ojbD.setdName("Admin");
//        ojbD.setEmplist(list);
//
//        session.save(ojbD);
//        txt.commit();
//        SessionFactory sessionFactory = new Configuration().
//                configure("hiber.config.xml").
//                addAnnotatedClass(Book.class).
//                addAnnotatedClass(Library.class).buildSessionFactory();
//        Session session = sessionFactory.openSession();
//        Transaction txt = session.beginTransaction();
//        Book b1 = new Book(101,"SitaRam",400);
//        Book b2 = new Book(102,"Mahabharat",1200.00);
//        List<Book> list= new ArrayList();
//        list.add(b1);
//        list.add(b2);
//        Library lib = new Library();
//        lib.setLibcode(1);
//        lib.setLibname("Public Library");
//        lib.setBookList(list);
//
//        session.save(lib);
//        txt.commit();
        SessionFactory sessionFactory= new Configuration().
                configure("hiber.config.xml").
                addAnnotatedClass(Employee.class).
                addAnnotatedClass(Department.class).
                buildSessionFactory();

        Session session =sessionFactory.openSession();


        Department  d= session.get(Department.class, 1);

        System.out.print( d.getdName());
        // System.out.print(d.getEmplist());
        System.out.println( "Hello World!" );
    }
}
