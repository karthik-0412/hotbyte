package com.hexaware; 
public class Arrays {
 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr= {56,34,23,89,100};
		int key=89;
		int resultIndex=linearSearch(arr,key);
		System.out.println(resultIndex);
 
	}
	
	
	static int linearSearch(int[] arr,int k) {
		for(int i=0;i<arr.length;i++) {
			if(arr[i]==k) {
				return i;
			}
		}
		return -1;
		
	}
	
	
 
}