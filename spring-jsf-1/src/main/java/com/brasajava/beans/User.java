package com.brasajava.beans;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.brasajava.beans.interfaces.Address;
import com.brasajava.beans.interfaces.Loggable;
import com.brasajava.beans.interfaces.Person;

@Entity
@Table(name="user")
public class User implements Person, Loggable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String name;
	private String firstLastName;
	private String secondLastName;
	private LocalDate birthday;
	@Column(unique=true, nullable=false)
	private String email;
	@Column(nullable=false)
	private String password;
	private boolean active;
	@OneToOne(targetEntity=AddressImpl.class, cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private Address address;
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Phone> phones;
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Email> emails;
	private BigDecimal credit;
	@ManyToMany(fetch=FetchType.EAGER, cascade= CascadeType.ALL)
	@JoinTable(name="permissions",joinColumns={@JoinColumn(name="userId")},inverseJoinColumns={@JoinColumn(name="permissionId")})
	private List<Permission> permissions;
	
	public void setId(long id){
		this.id = id;
	}
	public long getId(){
		return id;
	}

	@Override
	public void setName(String name) {
		this.name = name;		
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setFirstLastName(String firstLastName) {
		this.firstLastName = firstLastName;
	}

	@Override
	public String getFirstLastName() {
		return firstLastName;
	}

	@Override
	public void setSecondLastName(String secondLastName) {
		this.secondLastName = secondLastName;
	}

	@Override
	public String getSecondLastName() {
		return secondLastName;
	}

	@Override
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	@Override
	public LocalDate getBirthday() {
		return birthday;
	}
	@Override
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String getEmail() {
		return email;
	}
	@Override
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String getPassword() {
		return password;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	@Override
	public void setActive(boolean active) {
		this.active = active;
	}
	@Override
	public boolean isActive() {
		return active;
	}
	public List<Phone> getPhones() {
		return phones;
	}
	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}
	public List<Email> getEmails() {
		return emails;
	}
	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}
	public BigDecimal getCredit() {
		return credit;
	}
	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}
	public List<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", firstLastName=" + firstLastName + ", secondLastName="
				+ secondLastName + ", birthday=" + birthday + ", email=" + email + ", password=" + password
				+ ", active=" + active + ", address=" + address + ", phones=" + phones + ", emails=" + emails
				+ ", credit=" + credit + ", permissions=" + permissions + "]";
	}
	
}
