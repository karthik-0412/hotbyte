package org.springexample.SpringAnnotation;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
/**
 * Hello world!
 *
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext con= new AnnotationConfigApplicationContext(StudentConfig.class);
    	
    	
    	Student s=(Student)con.getBean("s1");
        System.out.println(s.toString() );
//        Student s1=(Student)con.getBean("s2");
//        System.out.println(s1.toString() );
        Collage c1=(Collage)con.getBean("c1");
        System.out.println(c1.toString() );
    }
}
