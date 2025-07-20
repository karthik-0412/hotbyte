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
//        service.nsaveRemove();
//        service.saveUpdate();
//        service.searchByRoll();
//        service.showData();
//        service.nshowData();
//        service.searchByName();
//        service.searchByNameMarks();
//        service.searchByNamegtMarks();
//        service.removebyRollNo();
//        service.saveUpdateByRoll();
//        service.nameshowData();
//        service.namesaveRemove();
//        service.nsaveUpdateByRoll();
        service.namesearchByName();
        System.out.println( "Hello World!" );
    }
}
