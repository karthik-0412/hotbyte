package Dao;

import connection.StudentConn;
import model.Student;
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
        query.setFirstResult(0);
        query.setMaxResults(5);
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
        Query<Student> query = session.createQuery("FROM Student WHERE name = :name", Student.class);
        query.setParameter("name", name);

        List<Student> students = query.list();

        if (students.isEmpty()) {
            System.out.println("No student found with name: " + name);
        } else {
            for (Student s : students) {
                System.out.println(s);
            }
        }

        txt.commit();

    }

    @Override
    public void searchByNameMark(String name, double marks) {
        Session session=factory.openSession();
        Transaction txt = session.beginTransaction();
        Query<Student> query = session.createQuery("FROM Student WHERE name = :name and marks= :marks", Student.class);
        query.setParameter("name", name);
        query.setParameter("marks",marks);
        List<Student> usersList=  query.list();

        for(Student  u : usersList )
        {
            System.out.println(u.toString());
        }
    }
    @Override
    public void searchByNamegtMark(double marks) {
        Session session=factory.openSession();
        Transaction txt = session.beginTransaction();
        Query<Student> query = session.createQuery("FROM Student WHERE marks > :marks", Student.class);

        query.setParameter("marks",marks);
        List<Student> usersList=  query.list();

        usersList.stream().filter(s ->  s.getMarks() > marks).forEach(s->System.out.println(s));
    }
    @Override
    public void removeByRollNo(int rollno)
    {
        Session session=factory.openSession();
        Transaction txTransaction=session.beginTransaction();
        Query <Student>Q=session.createQuery(" delete from Student where rollno=:rollno");
        Q.setParameter("rollno", rollno);
        int r=Q.executeUpdate();
        if(r>0)
        {
            System.out.println("Removed");
        }
        else {
            {
                System.out.println("Not Found");
            }
        }
        txTransaction.commit();


    }

    @Override
    public void updateByRollNo(String name,int roll){
        Session session=factory.openSession();
        Transaction txTransaction=session.beginTransaction();
        Query <Student>Q=session.createQuery(" update Student set name=:name where rollno=:rollno");
        Q.setParameter("name",name);
        Q.setParameter("rollno", roll);
        int r=Q.executeUpdate();
        if(r>0)
        {

            System.out.println("Updated");
        }
        else {
            {
                System.out.println("Not Found");
            }
        }
        txTransaction.commit();
    }

}
