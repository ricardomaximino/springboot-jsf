package com.brasajava.beans.interfaces;

public interface Customer {
	
	void setId(long id);
	long getId();
	void setFirstName(String firstName);
	String getFirstName();
	void setLastName(String lastName);
	String getLastName();
	void setAddress(Address address);
	Address getAddress();

}
