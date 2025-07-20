package org.springexample;

import service.StudentService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        StudentService service = new StudentService();
//        service.saveService();
//        service.saveRemove();
//        service.saveUpdate();
//        service.searchByRoll();
        service.showData();
//        service.searchByName();
//        service.searchByNameMarks();
//        service.searchByNamegtMarks();
//        service.removebyRollNo();
//        service.saveUpdateByRoll();
        System.out.println( "Hello World!" );
    }
}
