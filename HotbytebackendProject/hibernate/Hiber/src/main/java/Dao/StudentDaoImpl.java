package Dao;

import Connection.StudentConn;
import Model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class StudentDaoImpl implements StudentDao {

    SessionFactory factory;
    public StudentDaoImpl(){
        factory = StudentConn.getSessionFactory();
    }
    @Override
    public void saveData(Student s) {

        Session session = factory.openSession();
        Transaction txt = session.beginTransaction();
        session.save(s);
        txt.commit();

    }

    @Override
    public void removeByRoll(int rno) {

        Session session = factory.openSession();

        Transaction txt = session.beginTransaction();

        Student s = session.find(Student.class, rno);

        if (s == null) {
            System.out.println("Not Found");
        } else {
            {
                session.delete(s);
                txt.commit();
            }
        }
    }

    @Override
    public void updateByRoll(int rno,String name,double marks ) {
        Session session = factory.openSession();

        Transaction txt = session.beginTransaction();

        Student s = session.find(Student.class, rno);
        if (s == null) {
            System.out.println("Not Found");
        }
        else {
            s.setName(name);
            s.setMarks(marks);
            txt.commit();

        }
    }

    @Override
    public void searchByRoll(int rno) {
        Session session = factory.openSession();

        Transaction txt = session.beginTransaction();

        Student s = session.find(Student.class, rno);
        if (s == null) {
            System.out.println("Not Found");
        }
        else {
            System.out.println(s.toString());
            txt.commit();
        }
    }
    @Override
    public void ShowData() {


        Session session=factory.openSession();
        Query<Student> query= session.createQuery("from Student");

        List<Student> students=	 query.list();

        for(Student s : students )
        {
            System.out.println(s.toString());

        }


    }

    @Override
    public void searchByName(String name) {
        Session session=factory.openSession();
        Transaction txt = session.beginTransaction();
        Query<Student> query= session.createQuery("from Student where name = :name", Student.class);

        List<Student> students=	 query.list();

        for(Student s : students )
        {
            if (name.equals(s))System.out.println(s.toString());

        }
        txt.commit();

    }
}
