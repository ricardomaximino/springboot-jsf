package com.brasajava.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.brasajava.beans.interfaces.Address;

@Entity
@Table(name="address")
public class AddressImpl implements Address{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String country;
	private String state;
	private String city;
	private String town;
	private String street;
	private String number;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	@Override
	public String toString() {
		return "Address [id=" + id + ", country=" + country + ", city=" + city + ", street=" + street + "]";
	}
	@Override
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String getState() {
		return state;
	}
	@Override
	public void setTown(String town) {
		this.town = town;
	}
	@Override
	public String getTown() {
		return town;
	}
	@Override
	public void setNumber(String number) {
		this.number = number;
	}
	@Override
	public String getNumber() {
		return number;
	}
}
