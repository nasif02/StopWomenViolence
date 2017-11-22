package com.ewu.bug.swv.model;

public class Data {

	private int id;
	private String title;
	private String body;
	private int bookmark;
	
//	public XData(String title, String body) {
//		super();
//		this.title = title;
//		this.body = body;
//	}

	public Data(int id, String title, String body, int bookmark) {
		super();
		this.id = id;
		this.title = title;
		this.body = body;
		this.bookmark = bookmark;
	}
	
	

	@Override
	public String toString() {
		return "XData [id=" + id + ", title=" + title + ", body=" + body
				+ ", bookmark=" + bookmark + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getBookmark() {
		return bookmark;
	}

	public void setBookmark(int bookmark) {
		this.bookmark = bookmark;
	}
	
	
	
	
	
}
