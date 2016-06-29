package com.brasajava.beans;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Shopping {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@OneToOne(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	private User receiver;
	@OneToMany(cascade= CascadeType.ALL, fetch=FetchType.EAGER)
	private List<ShopItem> items;
	private String paymentType;
	private LocalDate date;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public User getReceiver() {
		return receiver;
	}
	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}
	public List<ShopItem> getItems() {
		return items;
	}
	public void setItems(List<ShopItem> items) {
		this.items = items;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}	
}
