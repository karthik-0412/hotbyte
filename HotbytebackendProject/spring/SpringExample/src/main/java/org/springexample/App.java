package org.springexample;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        ApplicationContext con = new ClassPathXmlApplicationContext("beans.xml");

        Student s1=(Student)con.getBean("s1");

        System.out.println(s1.toString());
//        Student s2=(Student)con.getBean("s2");
//
//        System.out.println(s2.toString());
//        Student s1=(Student)con.getBean("s1");

//        System.out.println(s1.toString());

        Address a=s1.getA1();

        System.out.println(a.toString());


    }
}
