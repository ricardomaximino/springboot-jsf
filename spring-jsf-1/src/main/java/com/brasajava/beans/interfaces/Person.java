package com.brasajava.beans.interfaces;

import java.time.LocalDate;

public interface Person {
	void setName(String name);
	String getName();
	void setFirstLastName(String firstLastName);
	String getFirstLastName();
	void setSecondLastName(String secondLastName);
	String getSecondLastName();
	void setBirthday(LocalDate birthday);
	LocalDate getBirthday();
}
