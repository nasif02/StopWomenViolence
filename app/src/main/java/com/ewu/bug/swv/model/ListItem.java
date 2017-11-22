package com.ewu.bug.swv.model;

public class ListItem {

	private int id;
	private String data;
	
	public ListItem(int id, String data) {
		super();
		this.id = id;
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		//return super.toString();
		
//		String s = this.data.replaceAll("\\p{P}\\p{S}", "");
		
		return this.data;
	}
	
	
	
	

}
