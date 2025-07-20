package java8_3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) {
		List<User> users = new ArrayList<>();

		users.add(new User(1, "Amit", 50000.0));
		users.add(new User(2, "Priya", 60000.0));
		users.add(new User(3, "Rahul", 55000.0));
		users.add(new User(4, "Sneha", 70000.0));
		users.add(new User(5, "Vikram", 48000.0));
		users.add(new User(6, "Pooja", 62000.0));
		users.add(new User(7, "Rohit", 75000.0));
		users.add(new User(8, "Neha", 58000.0));
		users.add(new User(9, "Ankit", 54000.0));
		users.add(new User(10, "Kiran", 67000.0));

		users.stream().forEach((u) -> System.out.println(u.toString()));
		users.stream().filter((e)->e.getSalary()>50000).forEach((u)->System.out.println("Salary > 50000 are: "+u.toString()));
//		List<User> u = users.stream()
//	            .filter(e->e.getSalary()+10000)
//	            .collect(Collectors.toList());
		 // 1️⃣ Names starting with 'P'
        List<String> names = users.stream()
            .map(user -> user.getName())
            .filter(name -> name.startsWith("P"))
            .collect(Collectors.toList());

        System.out.println("Names starting with 'P' are:");
        names.forEach(name -> System.out.println(name));

        // 2️⃣ Users with salary > ₹60,000
        List<User> high = users.stream()
            .filter(user -> user.getSalary() > 60000)
            .collect(Collectors.toList());

        System.out.println("\nUsers with salary > ₹60,000:");
        high.forEach(user -> System.out.println(user));

        // 3️⃣ Names in UPPERCASE
        List<String> up = users.stream()
            .map(user -> user.getName().toUpperCase())
            .collect(Collectors.toList());

        System.out.println("\nAll names in uppercase:");
        up.forEach(name -> System.out.println(name));

        // 4️⃣ Map of User IDs to Names
        Map<Object, Object> map = users.stream()
            .collect(Collectors.toMap(user -> user.getUid(), user -> user.getName()));

        System.out.println("\nMap of User IDs and Names:");
        
        map.forEach((id, name) -> System.out.println(id + " => " + name));
        
        System.out.println("Order in form of Name:  ");
        Collections.sort(users,(u1,u2)-> u1.getName().compareTo(u2.getName()) );
        
        users.forEach((e)-> System.out.println(e.toString()));
        System.out.println("Order in form of salary:  ");
        Collections.sort(users,(u1,u2)-> u1.getSalary().compareTo(u2.getSalary()) );
        
        users.forEach((e)-> System.out.println(e.toString()));
        System.out.println("Order in form of Max:  ");
        users.stream().max((u1,u2)-> Double.compare(u1.getSalary(),u2.getSalary()));
        users.forEach((e)-> System.out.println(e.toString()));
        
        System.out.println("Order in form of Min:  ");
        users.stream().min((u2,u1)-> Double.compare(u1.getSalary(),u2.getSalary()));
        users.forEach((e)-> System.out.println(e.toString()));

        
	}
}
