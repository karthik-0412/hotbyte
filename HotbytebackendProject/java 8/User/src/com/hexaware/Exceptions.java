package com.hexaware;

public class Exceptions {
	
	
	public static void main(String aa[])
	{
 
		
		
		   System.out.println("before  the exception ");
 
   int a=10,b=0;
   int c=0;
   
   
   try
   {
	   c=a/b;  
 
   }
   
   catch(Exception e)
   {
	   
	   System.out.println(e.getMessage());
 
	   
   }
   
   
   
   System.out.println("c = "+ c);
 
   
   System.out.println("after the exception ");
   
		
	}
 
}
 
 