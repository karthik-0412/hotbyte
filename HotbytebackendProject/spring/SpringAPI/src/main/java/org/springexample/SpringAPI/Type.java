package org.springexample.SpringAPI;

public class Type {
	 
	
	
	  String type;
	  int Range;
	  int top_speed;
	  
	  public Type() {
		
	}

	public Type(String type, int range, int top_speed) {
		super();
		this.type = type;
		Range = range;
		this.top_speed = top_speed;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getRange() {
		return Range;
	}

	public void setRange(int range) {
		Range = range;
	}

	public int getTop_speed() {
		return top_speed;
	}

	public void setTop_speed(int top_speed) {
		this.top_speed = top_speed;
	}

	@Override
	public String toString() {
		return "Type [type=" + type + ", Range=" + Range + ", top_speed=" + top_speed + "]";
	}
	  
	  
	  
	  
	  
}
