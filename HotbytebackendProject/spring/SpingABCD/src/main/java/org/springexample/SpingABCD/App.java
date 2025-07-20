package org.springexample.SpingABCD;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.springframework.context.support.AbstractApplicationContext;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//    	AbstractApplicationContext con = new ClassPathXmlApplicationContext("beans.xml");
// 	   Book b=(Book)con.getBean("b1");
//     System.out.println( b.toString() );
//     con.registerShutdownHook(); // Now this works
     ApplicationContext c = new ClassPathXmlApplicationContext("beans.xml");
	   Books b2=(Books)c.getBean("b2");
   System.out.println( b2.toString() );
    }
}
