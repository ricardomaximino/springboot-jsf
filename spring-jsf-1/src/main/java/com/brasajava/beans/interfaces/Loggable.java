package com.brasajava.beans.interfaces;

public interface Loggable {
	void setUsername(String email);
	String getUsername();
	void setPassword(String password);
	String getPassword();
	void setActive(boolean active);
	boolean isActive();
}
