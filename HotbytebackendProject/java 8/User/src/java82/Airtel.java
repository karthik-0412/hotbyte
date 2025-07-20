package java82;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Airtel {
	public static void main(String[] args) {
		String arr[] = {"Banana", "Jack fruit","Apple","Mango"};
		Arrays.stream(arr).forEach((i)-> System.out.println(i));
		Arrays.stream(arr).filter((i)->i.contains("fruit")).forEach((i)->System.out.println(i));
		double sal[] = {5000.00, 2000.00,3200.50,7000.68,2340.53,34550.34,2000.32,3500.53,3400.55,6450};
		Arrays.stream(sal).filter((i)->i > 5000).forEach((i)->System.out.println(i));
		String user[] = {"asha ", " pooja", " kavitha ", " deepa "};
		Arrays.stream(user).forEach((i)->System.out.println(i.trim()));
		
		List <Integer>Marks = new ArrayList();
		
		 Marks.add(2);
		 Marks.add(2);
		 Marks.add(21);
	 
		 Marks.add(22);
	 
		 Marks.add(23);
	 
		 Marks.add(87);
	 
		 Marks.add(90);
		
		// Marks.stream().forEach((e)->System.out.println(e));
		
		 Marks.stream().
		 filter((i)->i>=50).
		 forEach((e)->System.out.println(e));
		
		
			 Marks.stream().
			 map((i)->i+2).
			 forEach((e)->System.out.println(e));
			 
//			 double sal[] = {5000.00, 2000.00,3200.50,7000.68,2340.53,34550.34,2000.32,3500.53,3400.55,6450};
		Arrays.stream(sal).map((i)->i+i*0.05).forEach((i)->System.out.println(i));
		
		List<Integer> r = Marks.stream().map((i)->i+2).collect(Collectors.toList());
		System.out.println(r);
		
		
			
				List<String> users= new ArrayList();
				users.add("ASha");
				users.add("Pooja");
				users.add("kavita");
				users.add("deepa");
			   
				
				// create one list that list contains thouse name with start from the upper case
				 List<String> u = users.stream()
				            .filter(name -> Character.isUpperCase(name.charAt(0)))
				            .collect(Collectors.toList());
		 System.out.println(u);
	}

}
