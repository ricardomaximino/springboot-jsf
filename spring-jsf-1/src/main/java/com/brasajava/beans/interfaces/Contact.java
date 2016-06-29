package com.brasajava.beans.interfaces;

public interface Contact {
	void setId(long id);
	long getId();
	void setContact(String contact);
	String getContact();
	void setDescription(String description);
	String getDescription();
	void setMain(boolean main);
	boolean isMain();

}
