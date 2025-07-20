package Dao;

import Model.Student;

public interface StudentDao {
    void saveData(Student s);
    void removeByRoll(int rno);
    void updateByRoll(int rno,String name,double marks);
    void searchByRoll(int rno);
    void ShowData();
    void searchByName(String name);
}
