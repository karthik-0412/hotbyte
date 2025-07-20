package org.springexample.SpingABCD;

import java.util.List;
import java.util.Map;

public class Books {
		@Override
	public String toString() {
		return "Books [code=" + code + ", name=" + name + ", price=" + price + ", cityList=" + cityList + ", sales="
				+ sales + "]";
	}

		public Map<String, Integer> getSales() {
		return sales;
	}

	public void setSales(Map<String, Integer> sales) {
		this.sales = sales;
	}

		int code;
		String name;
		double price;
		List <String> cityList;
		Map<String, Integer> sales;
		
		Books()
		{
			
		}
	 
		public int getCode() {
			return code;
		}
	 
		public void setCode(int code) {
			this.code = code;
		}
	 
		public String getName() {
			return name;
		}
	 
		public void setName(String name) {
			this.name = name;
		}
	 
		public double getPrice() {
			return price;
		}
	 
		public void setPrice(double price) {
			this.price = price;
		}
	 
		public List<String> getCityList() {
			return cityList;
		}
	 
		public void setCityList(List<String> cityList) {
			this.cityList = cityList;
		}
	 
		
	 
		public Books(int code, String name, double price, List<String> cityList, Map<String, Integer> sales) {
			super();
			this.code = code;
			this.name = name;
			this.price = price;
			this.cityList = cityList;
			this.sales = sales;
		}

		
		
		
		
}
	 
	 
	


