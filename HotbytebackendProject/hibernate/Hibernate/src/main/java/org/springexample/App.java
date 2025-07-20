package org.springexample;

//import com.mysql.cj.Session;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
//import com.mysql.cj.xdevapi.SessionFactory;

/**
 * Hello world!
 *
 */
public class App 
{
    SessionFactory  sessionFactory;  // interface

    Session  session;
    public App() {

        sessionFactory= new Configuration().configure("hiber.config.xml").addAnnotatedClass(Student.class).buildSessionFactory();

    }


    void insert()
    {
        session=sessionFactory.openSession();

        Transaction txTransaction= session.beginTransaction();

        Student s= new Student();
        s.setRollno(106);
        s.setNameString("ajay");
        s.setMarks(10000.0);

        session.save(s);
        txTransaction.commit();
    }
    void search(int rollno)
    {

        session=sessionFactory.openSession();
        Student rs=	session.get(Student.class, rollno);
        if(rs!=null)
        {
            System.out.println(rs.toString());
        }
        else {
            System.out.println("Not Found");


        }

    }
    void searchByName(String name)
    {

        session=sessionFactory.openSession();
        Student sr=session.find(Student.class, name);
        if(sr!=null)
        {
            System.out.println(sr.toString());
        }
        else {
            System.out.println("Not Found");


        }
    }
    void removeByRollNo(int rno)
    {

        session=sessionFactory.openSession();
        Transaction txTransaction= session.beginTransaction();

        Student r=session.find(Student.class, rno);

        if(r==null)
        {
            System.out.println("Not Found");
        }
        else {


            session.delete(r);
        }

        txTransaction.commit();

    }
    void updateNameFee(int roll,double marks,String name)
    {

        session=sessionFactory.openSession();
        Transaction txTransaction= session.beginTransaction();
        Student r=session.find(Student.class, roll);

        if(r==null)
        {
            System.out.println("Not Found");
        }
        else {
            Student s = new Student();
            s.setRollno(roll);
            s.setNameString(name);
            s.setMarks(marks);

            session.update(s);
        }

        txTransaction.commit();


    }



    public static void main( String[] args )
    {




        App obj= new App();

//        obj.insert();
//        107  obj.search(106);
//obj.removeByRollNo(107);
//obj.updateNameFee(101,100,"ajay");
        obj.updateNameFee(108, 100, "Ram");


        System.out.println( "Hello World!" );
    }
}

