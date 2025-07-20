package org.springexample;

import Service.StudentService;

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
//        service.showData();
        service.searchByName();
        System.out.println( "Hello World!" );
    }
}
