package org.springexample.SpringAnnotation;

import org.springframework.context.annotation.Bean;

public class StudentConfig {
	@Bean(name="s1")
		public Student getStudent()
		{
			Student s= new Student();
			s.setRoll(101);
			s.setName("asha");
			s.setFee(2000.0);
			return s;
		}

	@Bean(name="c1")
	public Collage getCollage()
	{
		Collage c = new Collage();
		c.setC_id(20);
		c.setC_nm("KRM");
		
		return c;
	}
	
 

}
