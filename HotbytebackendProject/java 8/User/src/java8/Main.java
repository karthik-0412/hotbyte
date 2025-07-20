package java8;

public class Main {
 
	public static void main(String[] args) {
		
		
		
		 int a=20,b=50;
		  
		  Calculator sum=(x,y)-> x+y;
		  Calculator sub=(x,y)-> x-y;
		  
		  System.out.println(sum.cal(a, b));
		  System.out.println(sub.cal(a, b));
		
		
 
	}
 
}
 