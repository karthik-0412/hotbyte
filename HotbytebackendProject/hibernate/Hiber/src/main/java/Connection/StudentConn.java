package Connection;

import Model.Student;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class StudentConn {
    private static SessionFactory sessionFactory;
    StudentConn(){
        sessionFactory = new Configuration().configure("hiber.config.xml").addAnnotatedClass(Student.class).buildSessionFactory();
    }
    public static SessionFactory getSessionFactory(){
        StudentConn studentConn= new StudentConn();
        return sessionFactory;
    }
}
