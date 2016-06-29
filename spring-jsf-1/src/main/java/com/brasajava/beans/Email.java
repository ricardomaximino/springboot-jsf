package com.brasajava.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.brasajava.beans.interfaces.Contact;
@Entity
public class Email implements Contact{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String contact;
	private String description;
	private boolean main;

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public long getId() {
		return id;
	}
	@Override
	public void setContact(String contact) {
		this.contact = contact;
	}

	@Override
	public String getContact() {
		return contact;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setMain(boolean main) {
		this.main = main;
	}

	@Override
	public boolean isMain() {
		return main;
	}

}
