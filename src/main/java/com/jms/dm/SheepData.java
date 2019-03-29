package com.jms.dm;

public class SheepData {

	private String name;
	
	private String colour;
		
	public SheepData() {
		
	}

	public SheepData(String name, String colour) {
		this.name = name;
		this.colour = colour;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	@Override
	public String toString() {
		return "SheepData [name=" + name + ", colour=" + colour + "]";
	}
	
	
}
