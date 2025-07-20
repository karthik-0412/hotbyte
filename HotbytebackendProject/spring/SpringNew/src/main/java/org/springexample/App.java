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
        Payment s1=(Payment) con.getBean("s1");

        System.out.println(s1.toString());
        Payment s2=(Payment) con.getBean("s2");

        System.out.println(s2.toString());
    }
}
