package java82;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int a[] = {4, 6, 7, 3, 4, 5, 6, 7};

        Set<Integer> s = new HashSet<>();
        Set<Integer> dup = new HashSet<>();

        for (int num : a) {
            if (!s.add(num)) {
                dup.add(num); 
        }

	    }
        System.out.println("duplicate numbers in array a[]: " + dup);
	}
}